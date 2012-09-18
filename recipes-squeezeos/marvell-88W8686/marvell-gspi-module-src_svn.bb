DESCRIPTION = "Marvell 88W8686 gspi wlan driver"
SECTION = "base"
LICENSE = "binary only"

PV = "1.0"
PR = "r7"

PROVIDES = "marvell-gspi-module"

DEPENDS = "virtual/kernel"
RDEPENDS = "wireless-tools marvell-wlan-tools"

SRC_URI="${SQUEEZEOS_PRIVATE_SVN};module=src_gspi8686 \
	file://wlan-gspi \
	"

S = "${WORKDIR}/src_gspi8686"

inherit module-base

INHIBIT_PACKAGE_STRIP = "1"

CFLAGS_prepend += "-I${S}/os/linux"
CFLAGS_prepend += "-I${S}/wlan"

do_compile() {
	# Compile kernel modules
        # ${KERNEL_LD} doesn't understand the LDFLAGS, so suppress them
        oe_runmake CC="${KERNEL_CC}" LD="${KERNEL_LD}" LDFLAGS="" KERNELDIR=${KERNEL_SOURCE} MODDIR=/${base_libdir}/modules/${KERNEL_VERSION}
}

do_install() {
	# Install kernel modules
	install -m 0755 -d ${D}/${base_libdir}/modules/${KERNEL_VERSION}
	oe_runmake CC="${KERNEL_CC}" LD="${KERNEL_LD}" LDFLAGS="" KERNELDIR=${KERNEL_SOURCE} MODDIR=${D}/${base_libdir}/modules/${KERNEL_VERSION} install

	# Install wlan firmware
	install -m 0755 -d ${D}/${base_libdir}/firmware
	install -m 0644 ${S}/FwImage/gspi8686.bin ${D}/${base_libdir}/firmware/gspi8686.bin
	install -m 0644 ${S}/FwImage/helper_gspi.bin ${D}/${base_libdir}/firmware/helper_gspi.bin

	# Install init script
	install -m 0755 -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/wlan-gspi ${D}${sysconfdir}/init.d/wlan
}

PACKAGES = "marvell-gspi-module-dbg marvell-gspi-module"

FILES_marvell-gspi-module = "${base_libdir}/modules/${KERNEL_VERSION} ${base_libdir}/firmware ${sysconfdir}"
FILES_marvell-gspi-module-dbg = "${base_libdir}/modules/${KERNEL_VERSION}/.debug"
