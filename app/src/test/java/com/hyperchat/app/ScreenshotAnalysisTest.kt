package com.hyperchat.app

import com.hyperchat.app.domain.model.*
import com.hyperchat.app.data.repository.MockAIChatRepository
import org.junit.Assert.*
import org.junit.Test

/**
 * 截图分析和聊天复盘测试
 */
class ScreenshotAnalysisTest {

    private val repository = MockAIChatRepository()

    @Test
    fun testAnalyzeScreenshot_returnsValidAnalysis() {
        // Given
        val imageBytes = ByteArray(0)

        // When
        val analysis = runBlocking {
            repository.analyzeScreenshot(imageBytes)
        }

        // Then
        assertNotNull("分析结果不应为空", analysis)
        assertTrue("兴趣程度应在0-100之间",
            analysis.analysis.interestLevel in 0..100)
        assertTrue("防御程度应在0-100之间",
            analysis.analysis.defenseLevel in 0..100)
        assertTrue("语气不应为空", analysis.analysis.tone.isNotBlank())
        assertTrue("情绪状态不应为空", analysis.analysis.emotionState.isNotBlank())
    }

    @Test
    fun testAnalyzeScreenshot_hasSuggestions() {
        val analysis = runBlocking {
            repository.analyzeScreenshot(ByteArray(0))
        }

        assertTrue("应该包含建议", analysis.suggestions.isNotEmpty())
    }

    @Test
    fun testGetChatReviewReport_returnsValidReport() {
        // Given
        val messages = listOf("你好", "最近怎么样", "有空出来玩")

        // When
        val report = runBlocking {
            repository.getChatReviewReport(ConversationInfo(), messages)
        }

        // Then
        assertNotNull("复盘报告不应为空", report)
        assertTrue("情绪曲线应该有10个数据点",
            report.emotionCurve.size == 10)
        assertTrue("投入度应在0-100之间",
            report.investmentLevel in 0..100)
    }

    @Test
    fun testGetChatReviewReport_hasAnalysis() {
        val messages = listOf("嗨", "在吗", "出来玩")

        val report = runBlocking {
            repository.getChatReviewReport(ConversationInfo(), messages)
        }

        assertTrue("应该有关键转折点", report.keyTurningPoints.isNotEmpty())
        assertTrue("应该有改进建议", report.improvementSuggestions.isNotEmpty())
    }

    private fun <T> runBlocking(block: suspend () -> T): T {
        return kotlinx.coroutines.runBlocking { block() }
    }
}
