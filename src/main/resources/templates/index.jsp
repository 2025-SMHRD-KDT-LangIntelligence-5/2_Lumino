<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AI Portal - 최고의 AI 도구를 발견하세요</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <!-- Header -->
    <header>
        <div class="header-container">
            <a href="index.jsp" class="logo">
                <span>🤖</span>
                <span>AI Portal</span>
            </a>
            <nav>
                <ul>
                    <li><a href="index.jsp" class="active">홈</a></li>
                    <li><a href="tools.jsp">AI 도구</a></li>
                    <li><a href="categories.jsp">카테고리</a></li>
                    <li><a href="about.jsp">소개</a></li>
                </ul>
            </nav>
        </div>
    </header>

    <!-- Hero Section -->
    <div class="container">
        <div class="hero">
            <h1>최고의 AI 도구를 발견하세요</h1>
            <p>업무 생산성을 높이고 창의력을 향상시킬 수 있는 최신 AI 도구들을 찾아보세요</p>

            <!-- Search Bar -->
            <div class="search-container">
                <div class="search-box">
                    <input type="text" placeholder="AI 도구 검색..." id="searchInput">
                    <button onclick="searchTools()">검색</button>
                </div>
            </div>
        </div>

        <!-- Categories Filter -->
        <div class="categories-filter">
            <button class="category-btn active" onclick="filterCategory('all')">전체</button>
            <button class="category-btn" onclick="filterCategory('텍스트')">텍스트 생성</button>
            <button class="category-btn" onclick="filterCategory('이미지')">이미지 생성</button>
            <button class="category-btn" onclick="filterCategory('비디오')">비디오 편집</button>
            <button class="category-btn" onclick="filterCategory('음성')">음성 변환</button>
            <button class="category-btn" onclick="filterCategory('코딩')">코딩 도구</button>
            <button class="category-btn" onclick="filterCategory('분석')">데이터 분석</button>
        </div>

        <!-- Section Title -->
        <h2 class="section-title">인기 AI 도구</h2>

        <!-- Tools Grid -->
        <div class="tools-grid">
            <!-- Tool Card 1 -->
            <div class="tool-card" data-category="텍스트" onclick="location.href='tool-detail.jsp?id=1'">
                <div class="tool-header">
                    <div class="tool-icon">📝</div>
                    <div class="tool-info">
                        <h3>ChatGPT</h3>
                        <span class="tool-category">텍스트 생성</span>
                    </div>
                </div>
                <p class="tool-description">
                    강력한 대화형 AI로 글쓰기, 코딩, 분석 등 다양한 작업을 수행할 수 있습니다.
                </p>
                <div class="tool-footer">
                    <div class="tool-stats">
                        <span>⭐ 4.8</span>
                        <span>👥 1.2M</span>
                    </div>
                    <a href="tool-detail.jsp?id=1" class="tool-link">자세히 →</a>
                </div>
            </div>

            <!-- Tool Card 2 -->
            <div class="tool-card" data-category="이미지" onclick="location.href='tool-detail.jsp?id=2'">
                <div class="tool-header">
                    <div class="tool-icon">🎨</div>
                    <div class="tool-info">
                        <h3>Midjourney</h3>
                        <span class="tool-category">이미지 생성</span>
                    </div>
                </div>
                <p class="tool-description">
                    텍스트 프롬프트만으로 놀라운 예술 작품과 이미지를 생성하는 AI 도구입니다.
                </p>
                <div class="tool-footer">
                    <div class="tool-stats">
                        <span>⭐ 4.9</span>
                        <span>👥 850K</span>
                    </div>
                    <a href="tool-detail.jsp?id=2" class="tool-link">자세히 →</a>
                </div>
            </div>

            <!-- Tool Card 3 -->
            <div class="tool-card" data-category="코딩" onclick="location.href='tool-detail.jsp?id=3'">
                <div class="tool-header">
                    <div class="tool-icon">💻</div>
                    <div class="tool-info">
                        <h3>GitHub Copilot</h3>
                        <span class="tool-category">코딩 도구</span>
                    </div>
                </div>
                <p class="tool-description">
                    AI 기반 코드 자동완성 도구로 개발 속도를 획기적으로 향상시킵니다.
                </p>
                <div class="tool-footer">
                    <div class="tool-stats">
                        <span>⭐ 4.7</span>
                        <span>👥 1.5M</span>
                    </div>
                    <a href="tool-detail.jsp?id=3" class="tool-link">자세히 →</a>
                </div>
            </div>

            <!-- Tool Card 4 -->
            <div class="tool-card" data-category="음성" onclick="location.href='tool-detail.jsp?id=4'">
                <div class="tool-header">
                    <div class="tool-icon">🎤</div>
                    <div class="tool-info">
                        <h3>ElevenLabs</h3>
                        <span class="tool-category">음성 변환</span>
                    </div>
                </div>
                <p class="tool-description">
                    자연스러운 음성을 생성하고 다양한 언어로 변환할 수 있는 AI 도구입니다.
                </p>
                <div class="tool-footer">
                    <div class="tool-stats">
                        <span>⭐ 4.6</span>
                        <span>👥 420K</span>
                    </div>
                    <a href="tool-detail.jsp?id=4" class="tool-link">자세히 →</a>
                </div>
            </div>

            <!-- Tool Card 5 -->
            <div class="tool-card" data-category="비디오" onclick="location.href='tool-detail.jsp?id=5'">
                <div class="tool-header">
                    <div class="tool-icon">🎬</div>
                    <div class="tool-info">
                        <h3>Runway</h3>
                        <span class="tool-category">비디오 편집</span>
                    </div>
                </div>
                <p class="tool-description">
                    AI 기반 비디오 편집 도구로 전문가 수준의 영상을 쉽게 제작할 수 있습니다.
                </p>
                <div class="tool-footer">
                    <div class="tool-stats">
                        <span>⭐ 4.7</span>
                        <span>👥 650K</span>
                    </div>
                    <a href="tool-detail.jsp?id=5" class="tool-link">자세히 →</a>
                </div>
            </div>

            <!-- Tool Card 6 -->
            <div class="tool-card" data-category="분석" onclick="location.href='tool-detail.jsp?id=6'">
                <div class="tool-header">
                    <div class="tool-icon">📊</div>
                    <div class="tool-info">
                        <h3>DataRobot</h3>
                        <span class="tool-category">데이터 분석</span>
                    </div>
                </div>
                <p class="tool-description">
                    자동화된 머신러닝 플랫폼으로 복잡한 데이터 분석을 간단하게 수행합니다.
                </p>
                <div class="tool-footer">
                    <div class="tool-stats">
                        <span>⭐ 4.5</span>
                        <span>👥 320K</span>
                    </div>
                    <a href="tool-detail.jsp?id=6" class="tool-link">자세히 →</a>
                </div>
            </div>

            <!-- Tool Card 7 -->
            <div class="tool-card" data-category="텍스트" onclick="location.href='tool-detail.jsp?id=7'">
                <div class="tool-header">
                    <div class="tool-icon">✍️</div>
                    <div class="tool-info">
                        <h3>Jasper</h3>
                        <span class="tool-category">텍스트 생성</span>
                    </div>
                </div>
                <p class="tool-description">
                    마케팅 콘텐츠와 블로그 글을 빠르게 생성하는 AI 글쓰기 도구입니다.
                </p>
                <div class="tool-footer">
                    <div class="tool-stats">
                        <span>⭐ 4.6</span>
                        <span>👥 580K</span>
                    </div>
                    <a href="tool-detail.jsp?id=7" class="tool-link">자세히 →</a>
                </div>
            </div>

            <!-- Tool Card 8 -->
            <div class="tool-card" data-category="이미지" onclick="location.href='tool-detail.jsp?id=8'">
                <div class="tool-header">
                    <div class="tool-icon">🖼️</div>
                    <div class="tool-info">
                        <h3>DALL-E 3</h3>
                        <span class="tool-category">이미지 생성</span>
                    </div>
                </div>
                <p class="tool-description">
                    OpenAI의 최신 이미지 생성 AI로 높은 품질의 이미지를 생성합니다.
                </p>
                <div class="tool-footer">
                    <div class="tool-stats">
                        <span>⭐ 4.8</span>
                        <span>👥 920K</span>
                    </div>
                    <a href="tool-detail.jsp?id=8" class="tool-link">자세히 →</a>
                </div>
            </div>

            <!-- Tool Card 9 -->
            <div class="tool-card" data-category="코딩" onclick="location.href='tool-detail.jsp?id=9'">
                <div class="tool-header">
                    <div class="tool-icon">🔧</div>
                    <div class="tool-info">
                        <h3>Tabnine</h3>
                        <span class="tool-category">코딩 도구</span>
                    </div>
                </div>
                <p class="tool-description">
                    AI 기반 코드 완성 도구로 다양한 프로그래밍 언어를 지원합니다.
                </p>
                <div class="tool-footer">
                    <div class="tool-stats">
                        <span>⭐ 4.5</span>
                        <span>👥 780K</span>
                    </div>
                    <a href="tool-detail.jsp?id=9" class="tool-link">자세히 →</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <div class="footer-content">
            <div class="footer-section">
                <h3>AI Portal</h3>
                <p>최고의 AI 도구를 발견하고<br>생산성을 향상시키세요</p>
            </div>
            <div class="footer-section">
                <h3>바로가기</h3>
                <ul>
                    <li><a href="index.jsp">홈</a></li>
                    <li><a href="tools.jsp">AI 도구</a></li>
                    <li><a href="categories.jsp">카테고리</a></li>
                    <li><a href="about.jsp">소개</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h3>카테고리</h3>
                <ul>
                    <li><a href="categories.jsp?cat=text">텍스트 생성</a></li>
                    <li><a href="categories.jsp?cat=image">이미지 생성</a></li>
                    <li><a href="categories.jsp?cat=video">비디오 편집</a></li>
                    <li><a href="categories.jsp?cat=code">코딩 도구</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h3>문의</h3>
                <ul>
                    <li><a href="mailto:info@aiportal.com">이메일</a></li>
                    <li><a href="#">고객지원</a></li>
                    <li><a href="#">제휴문의</a></li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2024 AI Portal. All rights reserved.</p>
        </div>
    </footer>

    <script>
        function searchTools() {
            const searchTerm = document.getElementById('searchInput').value.toLowerCase();
            const cards = document.querySelectorAll('.tool-card');

            cards.forEach(card => {
                const text = card.textContent.toLowerCase();
                if (text.includes(searchTerm)) {
                    card.style.display = 'block';
                } else {
                    card.style.display = 'none';
                }
            });
        }

        function filterCategory(category) {
            const cards = document.querySelectorAll('.tool-card');
            const buttons = document.querySelectorAll('.category-btn');

            // Update active button
            buttons.forEach(btn => btn.classList.remove('active'));
            event.target.classList.add('active');

            // Filter cards
            cards.forEach(card => {
                if (category === 'all' || card.dataset.category === category) {
                    card.style.display = 'block';
                } else {
                    card.style.display = 'none';
                }
            });
        }

        // Search on Enter key
        document.getElementById('searchInput').addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                searchTools();
            }
        });
    </script>
</body>
</html>
