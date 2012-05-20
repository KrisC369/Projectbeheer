setLocal

del C:\projects\LIBS01~1\Local\CLIENT~1\HTMLApps\LIBRAR~2.APP\\_done.txt
pushd C:\VERSAT~1\VLS-5~1.6-J
call setEJBDeployEnvironment.bat
popd
set CLASSPATH=.\;.;C:\projects\LIBS01~1\Lib\CLIENT~1\HTMLApps\LIBRAR~2.APP\;C:\VERSAT~1\VLS-5~1.6-J\client\lib;C:\VERSAT~1\VLS-5~1.6-J\client\lib\vs-tle-client.jar;C:\VERSAT~1\VLS-5~1.6-J\client\lib\vs-tle-beans-client.jar;C:\VERSAT~1\VLS-5~1.6-J\vls\lib\vs-common-logging.jar;C:\VERSAT~1\VLS-5~1.6-J\vls\lib\aspectjrt.jar;%EJB_DEPLOY_CP%;C:\VERSAT~1\VLS-5~1.6-J\vls\lib\vs-tle-server.jar;C:\VERSAT~1\VLS-5~1.6-J\vls\lib\vs-tle-beans-server.jar;C:\VERSAT~1\VLS-5~1.6-J\vls\lib\vs-common-logging.jar;C:\VERSAT~1\VLS-5~1.6-J\VLS\lib\jsdk.jar;C:\VERSAT~1\VLS-5~1.6-J\VLS\lib\mail.jar;C:\VERSAT~1\VLS-5~1.6-J\VLS\lib\activation.jar;C:\VERSAT~1\VLS-5~1.6-J\Swing\swing.jar;C:\VERSAT~1\VLS-5~1.6-J\Swing\windows.jar;C:\VERSAT~1\VLS-5~1.6-J\vls\lib
C:\J2SDK1~1.2_0\bin\javac.exe %JAVAC_OPTIONS% -verbose -d C:\projects\LIBS01~1\Lib\CLIENT~1\HTMLApps\LIBRAR~2.APP\ @_compil.mk > C:\projects\LIBS01~1\Local\CLIENT~1\HTMLApps\LIBRAR~2.APP\\_log.txt 2>&1
set > C:\projects\LIBS01~1\Local\CLIENT~1\HTMLApps\LIBRAR~2.APP\\_done.txt

endLocal

