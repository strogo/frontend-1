class frontend {

  ##############################################################################
  # Basic apt packaging configuration
  ##############################################################################

  file {
    '/etc/apt/sources.list':
      source => 'puppet:///modules/frontend/etc/apt/sources.list.gb',
      owner  => 'root',
      group  => 'root',
      mode   => 0644;

    '/etc/apt/apt.conf.d/30timeout':
      source => 'puppet:///modules/frontend/etc/apt/apt.conf.d/30timeout',
      owner  => 'root',
      group  => 'root',
      mode   => 0644;

    '/etc/apt/apt.conf.d/31retries':
      source => 'puppet:///modules/frontend/etc/apt/apt.conf.d/31retries',
      owner  => 'root',
      group  => 'root',
      mode   => 0644;
  }

  exec {
    'apt-get update': command => '/usr/bin/apt-get update';
  }

  File['/etc/apt/sources.list'] -> Exec['apt-get update']
  File['/etc/apt/apt.conf.d/30timeout'] -> Exec['apt-get update']
  File['/etc/apt/apt.conf.d/31retries'] -> Exec['apt-get update']

  Exec['apt-get update'] -> Package<| |>


  ##############################################################################
  # Guardian configuration
  ##############################################################################

  file {
    '/etc/gu':
      ensure => directory,
      owner  => 'root',
      group  => 'root',
      mode   => 0755;

    '/etc/gu/install_vars':
      source => 'puppet:///modules/frontend/etc/gu/install_vars',
      owner  => 'root',
      group  => 'root',
      mode   => 0644;
  }


  ##############################################################################
  # Required packages
  ##############################################################################

  package {
    [
      'git',
      'curl',
      'build-essential',
      'openjdk-6-jdk',
      'graphicsmagick'
    ]: ensure => present;
  }


  ##############################################################################
  # Ruby
  ##############################################################################

  exec {
    'install-rvm':
      command => '/usr/bin/curl -L https://get.rvm.io | bash',
      creates => '/usr/local/rvm/bin/rvm',
      timeout => 0;

    'install-ruby-2.0.0-p247':
      command => '/usr/local/rvm/bin/rvm install 2.0.0-p247',
      creates => '/usr/local/rvm/rubies/ruby-2.0.0-p195/bin/ruby',
      timeout => 0;

    'set-default-ruby-2.0.0-p247':
      command => '/usr/local/rvm/bin/rvm alias create default ruby-2.0.0-p247';

    'use-ruby-2.0.0-p247':
       command => '/usr/local/rvm/bin/rvm use 2.0.0-p247';

    'install-gemset-ruby-2.0.0-p247-frontend':
      command => '/usr/local/rvm/bin/rvm gemset create frontend',
      creates => '/usr/local/rvm/gems/ruby-2.0.0-p247@frontend';

    'use-gemset-ruby-2.0.0-p247-frontend':
       command => '/usr/local/rvm/bin/rvm use 2.0.0-p247@frontend';
  }

  exec {
    'bundler':
      command => '/usr/local/rvm/bin/gem install bundler';

    'sass':
      command => '/usr/local/rvm/bin/gem install sass';

    'puppet-lint':
      command => '/usr/local/rvm/bin/gem install puppet-lint';

    'librarian-puppet':
      command => '/usr/local/rvm/bin/gem install librarian-puppet';
  }

  Package['curl'] ->
    Exec['install-rvm'] ->
    Exec['install-ruby-2.0.0-p247'] ->
    Exec['set-default-ruby-2.0.0-p247'] ->
    Exec['use-ruby-2.0.0-p247'] ->
    Exec['install-gemset-ruby-2.0.0-p247-frontend'] ->
    Exec['use-gemset-ruby-2.0.0-p247-frontend'] ->
    Exec['bundler', 'sass', 'puppet-lint', 'librarian-puppet']


  ##############################################################################
  # NodeJS
  ##############################################################################

  include nodejs

  package {
    'grunt-cli':
      ensure   => present,
      provider => npm
  }

  Class['nodejs'] -> Package['grunt-cli']
}