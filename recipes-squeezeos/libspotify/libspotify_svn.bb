DESCRIPTION = "libspotify"
SECTION = "libs"
LICENSE = "Confidential"

PV = "${DISTRO_VERSION}+svnr${SRCREV}"
PR = "r7"

# XXX this can be public later
SRC_URI="${SQUEEZEOS_PRIVATE_SVN};module=libspotify"

S = "${WORKDIR}/libspotify"

do_compile() {
}

EXTRA_OEMAKE_fab4="prefix=${D}/usr arch=arm11"

EXTRA_OEMAKE_baby="prefix=${D}/usr arch=arm9"

EXTRA_OEMAKE_jive="prefix=${D}/usr arch=arm9"

do_install() {
	oe_runmake install
}

FILES_${PN} = "${libdir}/lib*${SOLIBS}"


