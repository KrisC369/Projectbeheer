setLocal

del C:\projects\LIBS01~1\Local\VLS\_log.txt
del C:\projects\LIBS01~1\Local\VLS\_done.txt
pushd C:\VERSAT~1\VLS-5~1.6-J
call setEJBDeployEnvironment.bat
popd
set CLASSPATH=.\;C:\projects\LIBS01~1\Lib\VLS\;C:\projects\LIBS01~1\Lib\VLS\;C:\VERSAT~1\VLS-5~1.6-J\vls\lib\vs-tle-server.jar;C:\VERSAT~1\VLS-5~1.6-J\vls\lib\vs-tle-beans-server.jar;C:\VERSAT~1\VLS-5~1.6-J\vls\lib\vs-common-logging.jar;C:\VERSAT~1\VLS-5~1.6-J\VLS\lib\jsdk.jar;C:\VERSAT~1\VLS-5~1.6-J\VLS\lib\mail.jar;C:\VERSAT~1\VLS-5~1.6-J\VLS\lib\activation.jar;%EJB_DEPLOY_CP%
C:\J2SDK1~1.2_0\bin\javac.exe %JAVAC_OPTIONS% -verbose -d C:\projects\LIBS01~1\Lib\VLS\ @_compil.mk > C:\projects\LIBS01~1\Local\VLS\_log.txt 2>&1
set > C:\projects\LIBS01~1\Local\VLS\_done.txt

endLocal

