package com.hyperchat.app

import com.hyperchat.app.domain.model.*
import com.hyperchat.app.data.repository.MockAIChatRepository
import org.junit.Assert.*
import org.junit.Test

/**
 * AI回复建议测试
 */
class ReplySuggestionTest {

    private val repository = MockAIChatRepository()

    @Test
    fun testGetReplySuggestions_returnsThreeOptions() {
        // Given
        val conversationInfo = ConversationInfo(
            contactRole = ContactRole.FRIEND,
            chatGoal = ChatGoal.CLOSER_RELATION,
            chatStyle = ChatStyle.CASUAL
        )
        val theirMessage = "最近在忙什么？"

        // When
        val suggestions = runBlocking {
            repository.getReplySuggestions(conversationInfo, theirMessage, emptyList())
        }

        // Then
        assertTrue("应该返回3个建议", suggestions.size == 3)
        assertTrue("第一个建议应该有内容", suggestions[0].content.isNotBlank())
        assertTrue("第二个建议应该有内容", suggestions[1].content.isNotBlank())
        assertTrue("第三个建议应该有内容", suggestions[2].content.isNotBlank())
    }

    @Test
    fun testGetReplySuggestions_differentStyles() {
        // Given - 不同聊天风格
        val sincereInfo = ConversationInfo(chatStyle = ChatStyle.SINCERE)
        val humorousInfo = ConversationInfo(chatStyle = ChatStyle.HUMOROUS)

        // When
        val sincereSuggestions = runBlocking {
            repository.getReplySuggestions(sincereInfo, "你好", emptyList())
        }
        val humorousSuggestions = runBlocking {
            repository.getReplySuggestions(humorousInfo, "你好", emptyList())
        }

        // Then - 不同风格应该生成不同内容
        assertNotEquals(
            "不同风格应该生成不同的回复",
            sincereSuggestions[0].content,
            humorousSuggestions[0].content
        )
    }

    @Test
    fun testGetReplySuggestions_notEmpty() {
        val info = ConversationInfo(chatStyle = ChatStyle.PROFESSIONAL)
        val suggestions = runBlocking {
            repository.getReplySuggestions(info, "收到", emptyList())
        }

        assertTrue(suggestions.isNotEmpty())
        suggestions.forEach { suggestion ->
            assertTrue("建议内容不应为空", suggestion.content.isNotBlank())
            assertTrue("建议类型不应为空", suggestion.type.isNotBlank())
        }
    }

    private fun <T> runBlocking(block: suspend () -> T): T {
        return kotlinx.coroutines.runBlocking { block() }
    }
}
