The source code for [m.guardian.co.uk](m.guardian.co.uk). You want in? We're
[hiring](http://www.guardian.co.uk/workforus). Or check out CONTRIBUTING.md if
you like the independence.


Getting Started
---------------
Development is supported on Mac OS X 10.8 and Ubuntu 12.10 only. It may possibly
work without changes elsewhere but I can't really help you with that.

Broadly, the systems requirements are:

* Ruby 2.0.0-p247 via rvm
* Node.js and npm
* Grunt
* Bundler
* Sass
* GraphicsMagick


(A complete Vagrant VM definition capable of running the system is provided if
needed. Install VirtualBox and Vagrant and run `vagrant up` in the repository
root. Use `vagrant ssh` to connect to the guest and change to `/vagrant` and
continue below at Getting Started: Configuration.)


### Getting Started: Ubuntu 12.10
Run the setup script in `/dev`.

    cd dev
    sudo ./setup ubuntu


### Getting Started: Mac OS X 10.8
Sadly, Mac OS X setup is a little more involved than Ubuntu.

You need to have the XCode tools installed. If you don't, you can get the XCode
commandline tools [here](https://developer.apple.com/downloads).

You will also need Puppet. Follow the instructions
[here](http://docs.puppetlabs.com/guides/installation.html#mac-os-x) if you
don't. Install, the following in order:

* Facter: [facter-1.7.1.dmg](http://downloads.puppetlabs.com/mac/facter-1.7.1.dmg)
* Hiera: [hiera-1.2.1.dmg](http://downloads.puppetlabs.com/mac/hiera-1.2.1.dmg)
* Puppet: [puppet-3.2.2.dmg](http://downloads.puppetlabs.com/mac/puppet-3.2.2.dmg)


Then run the setup script in `/dev`.

    cd dev
    sudo ./setup macosx



### Getting Started: Configuration
You'll need a config file called `~/.gu/frontend.properties` with the following
content:

```
content.api.key=XXXX
content.api.host=http://XXXX
```

Guardian developers can collect a copy of `frontend.properties` with working
values from their kindly neighbourhood developer. External developers should
sign up for a Content API key [here](http://guardian.mashery.com).

Certain non-core functionality requires additional configuration:

````
pa.api.key=API key for Press Association feeds.

aws.access.key=AWS credential for infrastructure services
aws.access.secret.key=AWS credential for infrastructure services
aws.region=AWS region for infrastructure services

mongo.connection.password=A MongoDB connection string, funnily, thanks Grant!
aws.bucket=S3 bucket for simple persistence.
sns.notification.topic.arn=SNS notification topic for alerts.
````



### Getting Started: Running the Development Server
Change back to the repository root and start Simple Build Tool (sbt).

    ./sbt012

Once sbt is running (it may take a while first time), select the `dev-build`
uber project and run it (this too will take long the first time.)

    project dev-build
    run

Then take a trip to the F5 recompiling development server at
[localhost:9000](http://localhost:9000).

Code, reload, ship, repeat.

Further information on using the sbt/Play console is available
[here][play2-console].



### Getting Started: IntelliJ Project Files
The sbt-idea plugin is available, so generating IntelliJ project files can be
done in sbt with the `gen-idea` target:

    ./sbt012 gen-idea




Once You're Started
-------------------

### Deployment
Deployment uses the [Magenta][magenta] library.


### Debugging
You can debug your local Frontend application, by attaching a debugger.

* Start Simple Build Tool in debug mode by typing `./sbt012 --debug`
* Build and run your application.
* Use a debugger to attach to the remote Java process, on localhost:1044.

Any IDE debugger should be compatible. In IntelliJ, add a new Debug
Configuration, based on the Remote default. Ensure the Transport is Socket, the
Debugger mode is Attach, and the port is set to 1044. Start a new Debug session,
and your breakpoints should be active.


### Error: "too many files open"
On Linux machines you may run into a "too many files open" error during
compilation or reloading. You can find out how many file handles you are allowed
per process by running `ulimit -n`. This can be quite low, e.g. 1024.

To increase the limit on Ubuntu 12.10, add the following to
`/etc/security/limits.conf`:

```
*  soft  nofile 20000
*  hard  nofile 65000
```

And the following to `/etc/pam.d/common-session`:

```
session required pam_limits.so
```

And restart your machine.

See [here](http://www.cyberciti.biz/faq/linux-increase-the-maximum-number-of-open-files)
for more information.


[sbt]: http://www.scala-sbt.org
[play2-console]: https://github.com/playframework/Play20/wiki/PlayConsole
[play2-wiki]: https://github.com/playframework/Play20/wiki
[magenta]: https://github.com/guardian/deploy

