cd C:\\Users\\%USERNAME%\\Documents\\InterAACtionBoxAFSR\\GoogleChromePortable\\App\\Chrome-bin\\104.0.5.5112.81
powershell Add-Type -A 'System.IO.Compression.FileSystem';[IO.Compression.ZipFile]::ExtractToDirectory('chromeDll.zip', '../104.0.5.5112.81')
del chromeDll.zip