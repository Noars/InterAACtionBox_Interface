cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
rmdir /s /q interaactionGaze
powershell Start-BitsTransfer -Source "https://github.com/InteraactionGroup/interaactionGaze/releases/latest/download/interaactionGaze.zip" -Destination "interaactionGaze.zip"
powershell Add-Type -A 'System.IO.Compression.FileSystem';[IO.Compression.ZipFile]::ExtractToDirectory('interaactionGaze.zip', '../InterAACtionBoxAFSR')
del interaactionGaze.zip