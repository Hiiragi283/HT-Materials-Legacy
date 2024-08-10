package io.github.hiiragi283.bwms.api.extension

import com.google.common.collect.Table

inline fun <R : Any, C : Any, V : Any> Table<R, C, V>.forEach(action: (R, C, V) -> Unit) {
	cellSet().forEach { (row: R, column: C, value: V) -> action(row, column, value) }
}
