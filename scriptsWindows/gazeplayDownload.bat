cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
rmdir /s /q GazePlay
C:\"Program Files (x86)"\InterAACtionBoxAFSR\lib\wget\wget.exe -q -c --show-progress https://github.com/AFSR/GazePlay-AFSR/releases/latest/download/gazeplay-afsr-windows-x64.zip
powershell Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::ExtractToDirectory('gazeplay-afsr-windows-x64.zip', '../InterAACtionBoxAFSR')
powershell Get-Children gazeplay-afsr-* | Rename-Item -NewName "GazePlay"
del gazeplay-afsr-windows-x64.zip
exit