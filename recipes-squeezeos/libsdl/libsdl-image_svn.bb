DESCRIPTION = "SDL image library"
SECTION = "libs"
LICENSE = "LGPL"

LIC_FILES_CHKSUM="file://COPYING;md5=27818cd7fd83877a8e3ef82b82798ef4"

BV = "1.2.5"
SRCREV = "9704"
PV = "${BV}+svnr${SRCREV}"
PR = "r3"

# SDL has the hottest functions in the system, and Thumb is much slower for these
ARM_INSTRUCTION_SET = "arm"

SRC_URI="${SQUEEZEPLAY_SCM};module=SDL_image-${BV}"

S = "${WORKDIR}/SDL_image-${BV}"

DEPENDS = "libsdl jpeg libpng"
RDEPENDS = "libsdl jpeg libpng"

export SDL_CONFIG = "${STAGING_BINDIR_CROSS}/sdl-config"

inherit autotools

EXTRA_OECONF = "--disable-tif --disable-jpg-shared --disable-png-shared"

autotools_do_configure() {
	${S}/autogen.sh
	oe_runconf
}
