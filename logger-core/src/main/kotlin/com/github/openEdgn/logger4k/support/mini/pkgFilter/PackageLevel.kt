package com.github.openEdgn.logger4k.support.mini.pkgFilter

import com.github.openEdgn.logger4k.LoggerLevel
import java.util.LinkedList

class PackageLevel(private val superLevel: LoggerLevel) {
    private val root = PackageTree("", superLevel, false)

    @Synchronized
    fun putPackageLevel(pkgStr: String, level: LoggerLevel) {
        val list = LinkedList(pkgStr.split(".").map { it.lowercase() })
        putLevel(list, root, level)
    }

    private fun putLevel(list: LinkedList<String>, tree: PackageTree, level: LoggerLevel) {
        if (list.isEmpty()) {
            tree.level = level
            tree.empty = false
            return
        }
        var packageTree = tree.childNodes[list.first]
        if (packageTree != null) {
            list.removeFirst()
        } else {
            packageTree = PackageTree(list.first, tree.level, true)
            tree.childNodes[list.first] = packageTree
            list.removeFirst()
        }
        putLevel(list, packageTree, level)
    }

    fun getPackageLevel(pkgStr: String): LoggerLevel {
        val list = LinkedList(pkgStr.split(".").map { it.lowercase() })
        return getLevel(list, root, superLevel)
    }

    private fun getLevel(list: LinkedList<String>, tree: PackageTree, superLevel: LoggerLevel): LoggerLevel {
        val loggerLevel = if (tree.empty) {
            superLevel
        } else {
            tree.level
        }
        if (list.isEmpty()) {
            return loggerLevel
        }
        val packageTree = tree.childNodes[list.first]
        return if (packageTree != null) {
            list.removeFirst()
            getLevel(list, packageTree, loggerLevel)
        } else {
            superLevel
        }
    }

    data class PackageTree(
        val name: String,
        var level: LoggerLevel,
        var empty: Boolean,
        val childNodes: MutableMap<String, PackageTree> = HashMap()
    )
}
