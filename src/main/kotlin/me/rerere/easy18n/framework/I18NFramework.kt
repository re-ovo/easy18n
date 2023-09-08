package me.rerere.easy18n.framework

import com.intellij.psi.PsiElement

interface I18NFramework {
    fun isI18nCall(call: PsiElement): Boolean

    fun getI18nKey(call: PsiElement): String?
}