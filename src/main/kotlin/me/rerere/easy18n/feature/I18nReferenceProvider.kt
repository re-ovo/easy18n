package me.rerere.easy18n.feature

import com.intellij.lang.javascript.psi.JSLiteralExpression
import com.intellij.lang.javascript.psi.JSReferenceExpression
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.util.ProcessingContext

class I18nReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns
                .psiElement(JSLiteralExpression::class.java),
            I18nReferenceProvider
        )
    }
}

object I18nReferenceProvider: PsiReferenceProvider() {
    private val fnNames = setOf("t", "\$t")
    override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
        val fnName = element.parent.prevSibling ?: return PsiReference.EMPTY_ARRAY
        if(fnName is JSReferenceExpression && fnName.referenceName in fnNames) {
            val key = (element as JSLiteralExpression).stringValue
            println("i18n key = $key")
        }
        return PsiReference.EMPTY_ARRAY
    }
}