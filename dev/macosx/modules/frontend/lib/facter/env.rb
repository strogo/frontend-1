################################################################################
#
# Access environment variables in Puppet 3 using $env_ prefix.
#
# From: http://www.xenuser.org/open-source-development/using-environment-variables-in-puppet/
#
################################################################################

ENV.each do |k,v|
  Facter.add("env_#{k.downcase}".to_sym) do
    setcode do
      v
    end
  end
end