FOR /F "tokens=5 delims= " %%P IN ('netstat -a -n -o ^| findstr :8080.*LISTENING') DO taskkill /PID %%P /T /F
EXIT 0