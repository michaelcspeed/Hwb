#!/system/bin/sh
if ! applypatch -c EMMC:/dev/block/platform/omap/omap_hsmmc.0/by-name/recovery:5017600:ecee270cf329f3432856fce36ed008419bee1fb0; then
  log -t recovery "Installing new recovery image"
  applypatch -b /system/etc/recovery-resource.dat EMMC:/dev/block/platform/omap/omap_hsmmc.0/by-name/boot:4505600:3db34b9dc0235a39724dd22f694e8dddd13e3d88 EMMC:/dev/block/platform/omap/omap_hsmmc.0/by-name/recovery ecee270cf329f3432856fce36ed008419bee1fb0 5017600 3db34b9dc0235a39724dd22f694e8dddd13e3d88:/system/recovery-from-boot.p
else
  log -t recovery "Recovery image already installed"
fi
