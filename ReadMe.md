This is the main plugin for MicroManager 1.4

The whole RootScope- MicroManager project consists of 3 subprojects:

1.  plugin for MM
1.  grpc based server implementation (java 1.8)
1.  a client

Since the chosen RPC framework grpc (https://grpc.io/) uses a bunch of google libraries in newer versions which are also used by the micromanager in older releases, the server
implementation had to be separated.
The maven-shade plugin is used to avoid version clashes.
Hence the server project has to be installed in a local repository and subsequently be 
loaded into the plugin implementation.

The Client so far is also written in java but since grpc works across different languages
and platforms any other language also works.

As the rootscope runs on win7 32-bit currently no encryption is applied! The respective 
libraries (netty-tcnative) do not run on win 32 bit. (possible workarounds: own built of
http://netty.io/wiki/forked-tomcat-native.html or maybe jetty) 

What's implemented so far:
*  establishing a connection
*  skeleton of remote api calls

What's missing:
*  implementation of missing api calls
*  image analyses 