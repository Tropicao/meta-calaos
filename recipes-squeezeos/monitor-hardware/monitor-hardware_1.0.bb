DESCRIPTION = "monitor hardware"
SECTION = "base"
LICENSE = "GPL"

PR = "r2"

SRC_URI=" \
	file://Makefile \
	file://monitor_msp430.c \
	file://monitor_msp430.sh \
	"

S = "${WORKDIR}"

do_install() {
	install -m 0755 -d ${D}${sbindir}
	install -m 0755 -d ${D}${sysconfdir}
	install -m 0755 -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/monitor_msp430 ${D}${sbindir}/monitor_msp430
	install -m 0755 ${WORKDIR}/monitor_msp430.sh ${D}${sysconfdir}/init.d/monitor_msp430.sh
}
