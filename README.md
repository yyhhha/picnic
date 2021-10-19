![lastlogo](https://user-images.githubusercontent.com/87870107/137855336-1d08f8dd-a29c-4685-8c1b-2484c8841d4e.PNG)

# 피크닉 중개사이트 - 피그닉 사이트

---

# 🌷개요

피크닉대여업체를 메인으로 모아둔 플랫폼 사이트 입니다. 지친 현대인이 일상을 떠나 힐링할 수 있는 피크닉문화를 위해 **피그닉 사이트**가 만들어졌습니다. 편리하게 나에게 딱 맞는 피크닉 관련 정보를 얻고 지금 바로 피크닉을 떠나보세요.

- 피크닉 대여 업체
- 피크닉 추천 장소 정보
- 피크닉 장소 후기
- 피크닉 관련 꿀팁

### 우리팀 bgm

대아코 - 아무노래🎵  

### 우리팀 일정

[일정](https://www.notion.so/0ae98523590c4a4ebf5649ea2f01cd89)


---

# 🧷개발 스펙

- Spring
    - Boot
        - DB연동
- Vue
- junit
- Swagger

---

# 0️⃣기획

### 사용 툴

: [oven.io](http://oven.io) [[https://ovenapp.io/view/94SFGvlsJtOVHdZQzF7IjQYaFMfAXFjL/AirAq](https://ovenapp.io/view/94SFGvlsJtOVHdZQzF7IjQYaFMfAXFjL/AirAq)]

: [https://jsonformatter.org/json-viewer](https://jsonformatter.org/json-viewer)

: [https://www.erdcloud.com](https://www.erdcloud.com/)

: [https://ovenapp.io](https://ovenapp.io/)

: [https://convertio.co/kr/](https://convertio.co/kr/)

: [https://www.erdcloud.com/d/xssNYfrTFjFd9Nstx](https://www.erdcloud.com/d/xssNYfrTFjFd9Nstx)


### 기능명세서&화면기획안

![first](https://user-images.githubusercontent.com/87870107/137854848-7505c72b-da42-435f-a852-bf0340cd3d40.PNG)
   
    

### 기능

- 역할 분담
    
    ✅ **메인 @yh y** 
    
    - 테마별 추천
    - 회원가입페이지
    - 로그인페이지
    - 마이페이지
    - 비번확인 페이지
    - sidebar 구성

    
    ✅ **리스트 페이지 정렬 @지원** 
    
    - 대여업체, 피크닉장소 형식 비슷
    - 후기, 꿀팁 형식 비슷
    - 검색 결과 페이지(사진, 텍스트 둘다 혼합) + 슬라이드형식
    - sidebar 구성


     ✅ **글쓰기 페이지(수정)**지도API @서은박** 
    
    - 4개 모두 다른 input
    - 상세 페이지들
    - 대여업체, 피크닉장소
    - 후기 꿀팁 댓글


     ✅ **어드민 페이지 @대안 권** 
    
    - 리스트페이지, 수정
    - 삭제(email, user_out만 남기고 나머지 전부삭제)
   
 ---

# 1️⃣DB 구성

사용 툴: ERDCLOUD [[https://www.erdcloud.com/d/xssNYfrTFjFd9Nstx](https://www.erdcloud.com/d/xssNYfrTFjFd9Nstx)]

설계 : DDL 생성 후 프로젝트 진행

 ![diagramerd](https://user-images.githubusercontent.com/87870107/137858218-da336f6c-0313-4f7e-b169-ebcf1bdc7461.PNG)
    
    
    
---

# 2️⃣개발 이슈 히스토리
- FetchType 수정
- ManyToMany
- Spring Boot 문제
- 이미지 넣을때 참조
- session data 웹 출력 문제
- IOException 문제
- 양방향 관계에서 infinite recursion 해결
- select tag에서 option값 변화시마다 각각 다른 메서드 실행
- 로그아웃이후 다시 되돌아갈 경우 /해결
- Vue단에서 보내는 ? 클라인트가 보내는 것 변환안됐는데, 이거로 해결
- Vue를 이용해서 로그인버튼과 회원가입 버튼을 바꾸는 기능구현
- Vue 데이터에서 img 소스 가져올때
- reCAPTCHA 사용
- reCAPTCHA 값 문제
- dto이용해서 무한루프 안빠지고 json형태 가져오기
- Tomcat 오류 ... 서버가 안켜진다! → 해결
- Repository id로 딱 1개 나오는건 List 아니게 변경
- ORA-01861:literal does not match format string


---

# 3️⃣추가예정 기능

- 댓글
- 조회수
- 수정
- 이미지 AWS 구현
- 지도  api
- 그림찾기 캡차로 보안
- 2021년 느낌의 UI (현재는 06년에 머무르고 있음)
- 예외 처리


---

# 4️⃣회고

😇영훈**PM** [https://github.com/yyhhha](https://github.com/yyhhha)

> 프로젝트를 시작하면서 어떻게 프로젝트를 관리하고 진행을 해야 팀원들이 힘들지 않게 프로젝트를 완성할 수 있을까란 고민을 많이 했었습니다.
프로젝트가 진행되면서 생각하지 못했던 상황들이 계속 발생하고 기능이 정상작동 하지 않는 상황들이 있어서 혼자 마음속으로 걱정을 많이 했지만
제가 놓친 상황들에 대해서 설명해주시고 꼼꼼하게 체크해주시고 팀원분들 다들 성실하게 진행한 덕분에 프로젝트가 잘 마무리가 된거같습니다.
프로젝트를 진행하면서 팀원들과 더 친해진거 같고 프로젝트를 재밌게?? 했습니다.
부족한 모습이 많이 보였겠지만 다들 쓴소리 한번 없이 프로젝트 진행해주셔서 감사하고
다음 버전업을 기대하며 정해둔 기능 리스트들은 프로젝트 기간이 끝나더라도 팀원분들과 시간날때 완성 시키고 싶습니다.
최고의 팀원분들과 진행해서 좋은 프로젝트였습니다.!!!
> 

😎대안 [https://github.com/ptkeb](https://github.com/ptkeb)

> 1주일만에 많은 기술을 활용한 사이트를 만드는 것이기에 모두들 고생이 많았던 것 같습니다. 다들 의욕있게 참여해 주신데다가 코딩도 잘해주셔서 이번 프로젝트는 끝까지 나름 수월하게 진행되었던것 같습니다. 비록 시간관계상 구현하지못한 기능이 많이 있지만, 기존에 구상한것과 근접한 결과를 보니 정말 다들 열심히 참여했고, 잘 해냈다고 느끼고있습니다.  이번 프로젝트에서 여러가지 기술을 사용하면서 동시에 조원들과 깃, 줌을통해 협업을 진행하면서 정말 기억에 남을 프로젝트를 진행한것 같습니다.
> 

🤩지원 [https://github.com/jeewon-bang](https://github.com/jeewon-bang)

> 중간프로젝트를 통해서 vue와 rest api, JSON 포맷에 대해 익힐 수 있었습니다. 이전에 배웠던 것들의 총집합이었던 것 같습니다. 물론,, 프로젝트를 끝내고 나니 활용하지 못 해본 기술들이 아직 많은 것 같고 아쉬운 점이 분명있지만, 이후에도 기간 내 구현해보지 못 했던 기능들을 계속 develop하고 싶은 생각입니다. 무엇보다도.. 팀원들과 소통이 정말 원활해서 많이 의지할 수 있었고 기억에 오랫동안 남을 것 같은 프로젝트였습니다. (대아코의 왜들 그리 다운돼있어,,⭐)
> 

😋서은 [https://github.com/westsi1ver](https://github.com/westsi1ver)

> 이번 프로젝트를 통해서 더 많이 배웠고, 할 수 있는게 생긴 것 같아서 용기가 납니다. 특히 10개의 DB 테이블 ,정렬이랑 DTO 무한루프까지.. 험난한 과정을 함께 잘 거친 팀원들과 합이 잘 맞았다고 생각합니다. 비록 모든 기능과 예외사항을 처리하진 못했지만 다음 버전에서 더 기대되는 우리 사이트.. 애착이 참 많이 가요!(열정가득한 팀원들도! 그리고 지금보니까 PM님 덕분에 의견조율이 원활했던듯) 다음에는 스프링부트와 Vue의 장점을 최대한 활용할 수 있는 방식을 더 고려해보고싶네요.. 재밌었고 고마웠어요! 우리팀! (*힘들면 메인bgm 대아코 -아무노래 꼭 듣기*)
> 

