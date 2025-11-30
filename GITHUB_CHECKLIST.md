# GitHub 올리기 전 체크리스트

## ✅ 필수 확인 사항

### 1. Q클래스 무시 확인
- [x] `.gitignore`에 `generated/` 추가됨
- [x] `build.gradle`이 프로젝트 루트에 `generated/` 폴더 생성하도록 설정됨

### 2. 커밋할 파일 확인
다음 파일들이 커밋되어야 합니다:

#### 새로 생성한 파일들:
- [x] `src/main/java/.../review/dto/MyReviewResponseDto.java`
- [x] `src/main/java/.../review/repository/ReviewRepositoryCustom.java`
- [x] `src/main/java/.../review/repository/ReviewRepositoryCustomImpl.java`
- [x] `src/main/java/.../config/QuerydslConfig.java`
- [x] `src/main/resources/http/my-reviews-api-test.http` (선택사항)

#### 수정한 파일들:
- [x] `src/main/java/.../review/repository/ReviewRepository.java`
- [x] `src/main/java/.../review/controller/ReviewController.java`
- [x] `build.gradle`
- [x] `.gitignore`

### 3. Q클래스가 git에 추적되고 있는지 확인
만약 이전에 `build/generated/querydsl/`에 있던 Q클래스가 git에 올라갔다면:

```bash
# Q클래스가 git에 추적되고 있는지 확인
git ls-files | grep "Q.*\.java"

# 만약 나온다면 제거 (로컬 파일은 유지)
git rm -r --cached build/generated/querydsl/
```

### 4. `generated/` 폴더 확인
- 빌드 전에는 `generated/` 폴더가 없어도 됨 (빌드 시 자동 생성)
- 빌드 후 `generated/` 폴더가 생기지만 `.gitignore`로 무시됨
- **`generated/` 폴더가 git status에 나타나면 안 됨**

## 📝 커밋 명령어 예시

```bash
# 변경사항 확인
git status

# 스테이징
git add .

# 커밋 (커밋 컨벤션에 맞게)
git commit -m "✨ feat(review): QueryDSL로 내가 작성한 리뷰 보기 API 구현

- 가게별, 별점별 필터링 지원
- 하나의 API로 통합 설계
- Q클래스는 generated/ 폴더에 생성되도록 설정 (git ignore)"

# 푸시
git push origin [브랜치명]
```

## ⚠️ 주의사항

1. **Q클래스는 절대 커밋하면 안 됨**
   - `generated/` 폴더는 `.gitignore`로 무시됨
   - `build/generated/querydsl/`도 이미 `build/` 무시로 제외됨

2. **빌드 파일 커밋 안 하기**
   - `build/` 폴더 전체가 무시됨
   - `.class` 파일 등도 무시됨

3. **환경 설정 파일 주의**
   - `application.yml`의 DB 비밀번호 등은 실제로 올려도 되는지 확인

