package com.hyperchat.app

import com.hyperchat.app.domain.model.*
import com.hyperchat.app.data.repository.MockAIChatRepository
import org.junit.Assert.*
import org.junit.Test

/**
 * 对话策略测试
 */
class ConversationStrategyTest {

    private val repository = MockAIChatRepository()

    @Test
    fun testGetConversationStrategy_returnsValidStrategy() {
        // Given
        val conversationInfo = ConversationInfo(
            chatGoal = ChatGoal.INVITE_DINNER,
            contactRole = ContactRole.FRIEND
        )

        // When
        val strategy = runBlocking {
            repository.getConversationStrategy(conversationInfo, 1)
        }

        // Then
        assertTrue("阶段名称不应为空", strategy.phaseName.isNotBlank())
        assertTrue("阶段描述不应为空", strategy.phaseDescription.isNotBlank())
        assertTrue("当前阶段应该 >= 1", strategy.currentPhase >= 1)
        assertTrue("总阶段数应该 >= 1", strategy.totalPhases >= 1)
    }

    @Test
    fun testGetConversationStrategy_differentGoals() {
        // Given - 不同目标
        val inviteGoal = ConversationInfo(chatGoal = ChatGoal.INVITE_DINNER)
        val reconcileGoal = ConversationInfo(chatGoal = ChatGoal.RECONCILE)

        // When
        val inviteStrategy = runBlocking {
            repository.getConversationStrategy(inviteGoal, 1)
        }
        val reconcileStrategy = runBlocking {
            repository.getConversationStrategy(reconcileGoal, 1)
        }

        // Then - 不同目标应该有不同的策略
        assertNotEquals(
            "不同目标应该有不同策略",
            inviteStrategy.phaseName,
            reconcileStrategy.phaseName
        )
    }

    @Test
    fun testGetConversationStrategy_currentPhaseIncreases() {
        val info = ConversationInfo(chatGoal = ChatGoal.CLOSER_RELATION)

        val phase1 = runBlocking { repository.getConversationStrategy(info, 1) }
        val phase2 = runBlocking { repository.getConversationStrategy(info, 2) }

        assertEquals("第一阶段应该是1", 1, phase1.currentPhase)
        assertEquals("第二阶段应该是2", 2, phase2.currentPhase)
    }

    private fun <T> runBlocking(block: suspend () -> T): T {
        return kotlinx.coroutines.runBlocking { block() }
    }
}
