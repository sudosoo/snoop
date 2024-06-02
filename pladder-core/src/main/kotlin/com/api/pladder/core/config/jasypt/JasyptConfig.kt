package com.api.pladder.core.config.jasypt

/*
@Configuration
class JasyptConfig (
    @Value("\${jasypt.encryptor.password}")
    private var password: String? = null
){

    @Bean("jasyptStringEncryptor")
    fun stringEncryptor(): StringEncryptor {
        val config = StandardPBEStringEncryptor()
        config.setPassword(password)
        config.setAlgorithm("PBEWithMD5AndDES")
        config.setKeyObtentionIterations(1000)
        config.setProviderName("SunJCE")
        config.setSaltGenerator(RandomSaltGenerator())
        config.setIvGenerator(NoIvGenerator())
        config.setStringOutputType("base64")
        return config
    }
}*/
