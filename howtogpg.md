# How to understand GPG key

This is used to sign the commit to a server (eg. Github)

here are some of the command that was use to set up a GPG key locally

```
* to view the version try the following
gpg --version

* to generate a full GPG key try the following
gpg --full-generate-key

* to view the list of all GPG key in LONG format use the following
gpg --list-keys --keyid-format LONG

* to view the list of Secret keys try the following
gpg --list-secret-keys --keyid-format LONG

* to export the GPG key 
*gpg --armor --export C9E2BE8E86C74EC0

* to setup Git at global level to use gpg signing for all commits use the following
git config --global commit.gpgSign true
```
