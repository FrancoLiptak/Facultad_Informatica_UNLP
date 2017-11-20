# Trabajo práctico 4

## Gemas y Bundler

### 1. ¿Qué es una gema? ¿Para qué sirve? ¿Qué estructura tienen?

The RubyGems software allows you to easily download, install, and use ruby software packages on your system. The software package is called a “gem” which contains a packaged Ruby application or library.

Gems can be used to extend or modify functionality in Ruby applications. Commonly they’re used to distribute reusable functionality that is shared with other Rubyists for use in their applications and libraries. Some gems provide command line utilities to help automate tasks and speed up your work.

STRUCTURE OF A GEM

Each gem has a name, version, and platform. Platforms are based on the CPU architecture, operating system type and sometimes the operating system version. Examples include “x86-mingw32” or “java”. The platform indicates the gem only works with a ruby built for the same platform.

Inside gems are the following components:

- Code (including tests and supporting utilities)
- Documentation
- gemspec

Each gem follows the same standard structure of code organization:

```
% tree freewill
freewill/
├── bin/
│   └── freewill
├── lib/
│   └── freewill.rb
├── test/
│   └── test_freewill.rb
├── README
├── Rakefile
└── freewill.gemspec

```

Here, you can see the major components of a gem:

- The lib directory contains the code for the gem

- The test or spec directory contains tests, depending on which test framework the developer uses

- A gem usually has a Rakefile, which the rake program uses to automate tests, generate code, and perform other tasks.

- This gem also includes an executable file in the bin directory, which will be loaded into the user’s PATH when the gem is installed.

- Documentation is usually included in the README and inline with the code. When you install a gem, documentation is generated automatically for you. Most 
gems include RDoc documentation, but some use YARD docs instead.

- The final piece is the gemspec, which contains information about the gem. The gem’s files, test information, platform, version number and more are all laid out here along with the author’s email and name.

http://guides.rubygems.org/what-is-a-gem/

### 2. ¿Cuáles son las principales diferencias entre el comando gem y Bundler? ¿Hacen lo mismo?
### 3. ¿Dónde instalan las gemas los comandos gem y bundle?

The purpose of bundle install is to setup everything for the application containing the Gemfile. You can even pass arguments to make needed gems installed in whatever folder you want.

This way in production, you have clearly separated apps with their own gems.

On the other side, gem install gmaps4rails (easy advertisement) gets the gem installed for your whole environment.

### 4. ¿Para qué sirve el comando gem server? ¿Qué información podés obtener al usarlo?

http://guides.rubygems.org/run-your-own-gem-server/

### 5. Investigá un poco sobre Semantic Versioning (o SemVer). ¿Qué finalidad tiene? ¿Cómo se compone una versión? ¿Ante qué situaciones debería cambiarse cada una de sus partes?

https://semver.org/

