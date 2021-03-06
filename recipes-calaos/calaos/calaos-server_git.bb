DESCRIPTION = "Calaos Graphical User Insterface"
HOMEPAGE = "http://www.calaos.fr"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS += "libsigc++-2.0 owfs jansson luajit libusb ola knxd libuv curl"
RDEPENDS_${PN} += "heyu lua-socket ola knxd imagemagick curl"

PE = "1"
PV = "3.0-alpha6+${PR}+git${SRCPV}"

SRCREV = "a7ed577ac11725d088a814bf1d125703c41f2034"

SECTION = "utils"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/calaos/calaos_base.git;protocol=http;branch=master \
           file://calaos-server.service \
           file://calaos_migrate.sh \
           "

inherit pkgconfig autotools gettext systemd

do_configure_prepend() {
  (cd ${S}
  autopoint --force
  autoreconf -vif)
}

do_install_append() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/calaos-server.service ${D}${systemd_unitdir}/system

	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/calaos_migrate.sh ${D}${bindir}
}

FILES_${PN} = "${bindir}/calaos_server \
               ${systemd_unitdir}/system/calaos-server.service \
               ${bindir}/calaos_* \
               ${bindir}/wago_test \
               ${datadir}/calaos/camfail.jpg \
               ${datadir}/calaos/debug/* \
               "

SYSTEMD_SERVICE_${PN} = "calaos-server.service"
