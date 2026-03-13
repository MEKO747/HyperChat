# HyperChat

高情商对话辅助系统 - 基于AI的聊天策略助手

## 项目简介

HyperChat 是一款 Android 应用，为用户在现实社交场景中提供实时策略建议与话术生成。

## 功能特性

- **聊天辅助**：根据场景和目标获取AI回复建议（理解型/轻松型/目标推进型）
- **截图分析**：上传聊天截图，AI智能分析关系动态
- **聊天复盘**：分析聊天记录，提供改进建议
- **高情商库**：内置各场景高情商表达示例

## 技术栈

| 分类 | 技术 |
|------|------|
| 语言 | Kotlin |
| UI | Jetpack Compose |
| 架构 | MVVM + Clean Architecture |
| DI | Hilt |
| 数据库 | Room |
| 网络 | Retrofit + OkHttp |
| AI | MiniMax API |

## 项目结构

```
app/src/main/java/com/hyperchat/app/
├── data/                    # 数据层
│   ├── local/              # 本地存储
│   ├── remote/             # API调用
│   └── repository/         # 仓库实现
├── domain/                  # 领域层
│   ├── model/              # 数据模型
│   └── repository/         # 仓库接口
├── di/                      # 依赖注入
└── ui/                     # UI层
    ├── screens/             # 页面
    ├── navigation/         # 导航
    └── theme/              # 主题
```

## 快速开始

### 环境要求

- Android Studio Arctic Fox+
- JDK 17+
- Gradle 8.5+

### 编译运行

```bash
# 克隆项目
git clone https://github.com/MEKOELITE/HyperChat.git

# 进入目录
cd HyperChat

# 编译Debug APK
./gradlew assembleDebug

# 安装APK
adb install app/build/outputs/apk/debug/app-debug.apk
```

### 配置API

1. 访问 [MiniMax开放平台](https://platform.minimaxi.chat) 注册账号
2. 创建API Key
3. 打开App → 我的 → 设置 → 输入API Key

## 开发指南

### 添加依赖

在 `build.gradle.kts` 中添加：

```kotlin
implementation("com.hyperchat.app:core:1.0.0")
```

### 运行测试

```bash
./gradlew test
```

## License

MIT License
