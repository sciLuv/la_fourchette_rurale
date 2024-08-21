:: Adapter les valeurs des 3 variables.
set jboss_root=C:\Servers\jboss-eap-7.4
set ear_module_name=LaFourchetteRurale-EAR
set ear_archive_name=LaFourchetteRurale-EAR-1.0-SNAPSHOT.ear

cd %jboss_root%\bin
cmd /c jboss-cli.sh --connect command=:shutdown
timeout /t 3
cd %jboss_root%\standalone
@RD /S /Q deployments
mkdir deployments
cd %~dp0
cmd /c mvn clean install
cd %ear_module_name%\target
copy %ear_archive_name% %jboss_root%\standalone\deployments
cd %jboss_root%\bin
cmd /c standalone.bat