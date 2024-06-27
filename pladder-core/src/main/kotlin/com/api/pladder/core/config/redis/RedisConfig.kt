package com.api.pladder.core.config.redis

import io.lettuce.core.ReadFrom.REPLICA_PREFERRED
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration


@Configuration
class RedisConfig {
    @Value("\${redis_host}")
    private val host: String? = null

    @Value("\${redis_port}")
    private val port: Int? = null

    @Bean
    fun redisConnectionFactoryDev(): LettuceConnectionFactory {
        val clientConfig = LettuceClientConfiguration.builder()
            .readFrom(REPLICA_PREFERRED)
            .commandTimeout(Duration.ofSeconds(10))
            .build()

        val serverConfig = RedisStaticMasterReplicaConfiguration(host!!, port!!)

        return LettuceConnectionFactory(serverConfig, clientConfig)
    }

    @Bean
    fun redisCacheConfiguration(): RedisCacheConfiguration {
        return RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    Jackson2JsonRedisSerializer(String::class.java)
                )
            )
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    Jackson2JsonRedisSerializer(String::class.java)
                )
            )
            .disableCachingNullValues()
    }

    @Bean
    @Qualifier("redisCacheManager")
    fun redisCacheManagerDev(): CacheManager {
        return RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactoryDev())
            .build()
    }


}