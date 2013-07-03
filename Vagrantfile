Vagrant.configure("2") do |config|

  config.vm.hostname = "frontend"

  config.vm.box = "frontend_quantal64"
  config.vm.box_url = "https://github.com/downloads/roderik/VagrantQuantal64Box/quantal64.box"

  config.ssh.forward_x11 = true

  config.vm.network :forwarded_port, host: 8000, guest: 80
  config.vm.network :forwarded_port, host: 9000, guest: 9000
  config.vm.network :forwarded_port, host: 18080, guest: 18080


  config.vm.provider :virtualbox do |virtualbox|
    virtualbox.name = "frontend"
    #virtualbox.gui = true

    virtualbox.customize ["modifyvm", :id, "--memory", "2048"]
  end

  config.vm.provision :puppet, :options => [
      "--modulepath=/vagrant/dev/ubuntu/modules",
      "--debug"
  ] do |puppet|
    puppet.manifests_path = "dev"
    puppet.manifest_file = "site.pp"
  end
end
