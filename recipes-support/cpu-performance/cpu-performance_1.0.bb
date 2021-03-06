DESCRIPTION = "Force CPU governor to performance"
HOMEPAGE = "http://www.calaos.fr"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING;md5=d32239bcb673463ab874e80d47fae504"

PR = "r2"

SECTION = "base"

SRC_URI = "file://cpu-performance.service \
           file://cpuperf \
           file://COPYING"

inherit systemd

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/cpu-performance.service ${D}${systemd_unitdir}/system
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/cpuperf ${D}${bindir}
}

SYSTEMD_SERVICE_${PN} = "cpu-performance.service"

