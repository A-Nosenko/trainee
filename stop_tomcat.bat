%@Try%
for /f "tokens=5" %a in ('netstat -aon ^| find ":8080" ^| find "LISTENING"') do taskkill /f /pid %a
%@EndTry%
:@Catch
exit 0
:@EndCatch
