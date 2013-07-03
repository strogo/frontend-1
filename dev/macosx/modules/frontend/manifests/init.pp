class frontend {

  ##############################################################################
  # Basic homebrew packaging configuration
  ##############################################################################

  exec {
    'install-homebrew':
      command => '/bin/echo | ruby -e "$(curl -fsSL https://raw.github.com/mxcl/homebrew/go | grep -v \'t run this as root\')"',
      creates => '/usr/local/bin/brew';
  }


  ##############################################################################
  # Guardian configuration
  ##############################################################################

  file {
    '/etc/gu':
      ensure => directory,
      owner  => 'root',
      group  => 'wheel',
      mode   => 0755;

    '/etc/gu/install_vars':
      source => 'puppet:///modules/frontend/etc/gu/install_vars',
      owner  => 'root',
      group  => 'wheel',
      mode   => 0644;
  }


  ##############################################################################
  # Required packages
  ##############################################################################

  exec {
    'install-graphicsmagick':
      command     => '/usr/local/bin/brew install graphicsmagick',
      environment => "HOME=$env_home",
      creates     => '/usr/local/bin/GraphicsMagick-config';
  }

  Exec['install-homebrew'] -> Exec['install-graphicsmagick']


  ##############################################################################
  # Ruby
  ##############################################################################

  # TODO Add /usr/local/rvm/bin to path of users

  exec {
    'install-rvm':
      command => '/usr/bin/curl -L https://get.rvm.io | bash',
      creates => '/usr/local/rvm/bin/rvm',
      timeout => 0;

    'install-ruby-2.0.0-p247':
      command     => '/usr/local/rvm/bin/rvm install 2.0.0-p247',
      creates     => '/usr/local/rvm/rubies/ruby-2.0.0-p195/bin/ruby',
      environment => "HOME=$env_home",
      timeout     => 0;

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

  exec {
    'install-node':
      command     => '/usr/local/bin/brew install node',
      environment => "HOME=$env_home",
      creates     => '/usr/local/bin/node';
  }

#  package {
#    'grunt-cli':
#      ensure   => present,
#      provider => npm
#  }

  Exec['install-homebrew'] -> Exec['install-node'] # -> Exec['install-grunt']
}
