@REM=======================================================
@REM=  Name: run.cmd
@REM=  Description: This script sets up the enviroment and 
@REM=               executes AStarPathfind
@REM=  Input: 
@REM=  ProductionVersion:
@REM===========================================================
@REM=  Version  Author  			Date    		Description
@REM===========================================================
@REM=  1.0      Abdul Patel			2013-03-22		Created
@REM===========================================================

@ECHO OFF

REM if "%1" == "" GOTO usage

REM Specify your java installation folder
set JAVA_HOME=C:\Program Files (x86)\Java\jdk1.6.0_45
REM set JAVA_HOME=C:\

IF NOT EXIST %JAVA_HOME% GOTO NOJAVAHOME


SET CURR_DIR=%cd%
SET TOOLS_HOME=%CURR_DIR%\tools
SET LIBRARIES_HOME=%WORKSPACE_HOME%\lib
SET ANT_HOME=%TOOLS_HOME%\apache-ant-1.8.2
SET ANT_OPTS=-Xmx1024m -XX:MaxPermSize=256m -XX:PermSize=128m

PATH=%JAVA_HOME%\bin;%ANT_HOME%\bin

GOTO verbose



:usage
ECHO Usage: run  {map-filename}
GOTO finish

:NOJAVAHOME
ECHO Can't find Java Home (%JAVA_HOME%), please specify a JAVA_HOME in run.cmd
EXIT /B

:verbose

ECHO ==================================================
ECHO TOOLS_HOME=%TOOLS_HOME%
ECHO ANT_HOME=%ANT_HOME%
ECHO ANT_OPTS=%ANT_OPTS%
ECHO JAVA_HOME=%JAVA_HOME%
ECHO PATH=%PATH%
ECHO ==================================================

if NOT "%1" == "" GOTO run-custom
  ant run-test
  
EXIT /B  

:run-custom
 ant -Dfile=%1 run 

:end