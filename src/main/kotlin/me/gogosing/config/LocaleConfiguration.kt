package me.gogosing.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.LocaleResolver
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by JinBum Jeong on 2020/03/04.
 */
@Configuration
class LocaleConfiguration {

    @Bean
    fun localeResolver(): LocaleResolver {
        return LocaleWithHeaderLocaleResolver()
    }
}

class LocaleWithHeaderLocaleResolver : LocaleResolver {

    override fun resolveLocale(request: HttpServletRequest): Locale {
        val headerValue = request.getHeader("X-Locale-With")
        return when (headerValue?.toLowerCase()) {
            "en" -> Locale.ENGLISH
            "ko" -> Locale.KOREAN
            "ja" -> Locale.JAPANESE
            "zh" -> Locale.CHINESE
            else -> Locale.ENGLISH
        }
    }

    @Throws(UnsupportedOperationException::class)
    override fun setLocale(request: HttpServletRequest, response: HttpServletResponse?, locale: Locale?) {
        throw UnsupportedOperationException("Cannot change locale")
    }
}
