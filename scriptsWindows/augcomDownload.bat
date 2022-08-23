cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR
rmdir /s /q AugCom
powershell [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; wget https://github.com/InteraactionGroup/InterAACtionScene/releases/latest/download/AugCom.zip -OutFile "AugCom.zip"
powershell Add-Type -A 'System.IO.Compression.FileSystem'; [IO.Compression.ZipFile]::ExtractToDirectory('AugCom.zip', '../InterAACtionBoxAFSR')
del AugCom.zip