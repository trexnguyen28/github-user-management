package vn.trex.user.manager

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform