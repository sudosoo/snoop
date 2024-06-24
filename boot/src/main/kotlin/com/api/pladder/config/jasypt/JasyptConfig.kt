package com.api.pladder.config.jasypt

import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.jasypt.iv.NoIvGenerator
import org.jasypt.salt.RandomSaltGenerator
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class JasyptConfig{
    @Value("\${jasypt.encryptor.password}")
    private var password: String? = null

    @Bean("jasyptStringEncryptor")
    fun stringEncryptor(): StringEncryptor {
        val encryptor = PooledPBEStringEncryptor()
        val config = SimpleStringPBEConfig()
        config.poolSize = 1
        config.password = password
        config.algorithm = "PBEWithMD5AndDES"
        config.keyObtentionIterations = 1000
        config.providerName = "SunJCE"
        config.saltGenerator = RandomSaltGenerator()
        config.ivGenerator = NoIvGenerator()
        config.stringOutputType = "base64"
        encryptor.setConfig(config);
        return encryptor
    }


}