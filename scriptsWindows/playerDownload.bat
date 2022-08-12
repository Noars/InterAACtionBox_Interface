cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
rmdir /s /q InterAACtionPlayer
powershell Start-BitsTransfer -Source "https://github.com/InteraactionGroup/InterAACtionPlayer/releases/latest/download/InterAACtionPlayer.zip" -Destination "InterAACtionPlayer.zip"
powershell Add-Type -A 'System.IO.Compression.FileSystem';[IO.Compression.ZipFile]::ExtractToDirectory('InterAACtionPlayer.zip', '../InterAACtionBoxAFSR')
del InterAACtionPlayer.zip