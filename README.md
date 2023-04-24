# 품앗이 - 경조사 다이어리

결혼식, 생일, 돌잔치, 장례식 등 각종 경조사에서 주고 받은 내역을 손쉽게 관리할 수 있는 앱입니다. <br>
다른 유저들의 통계를 참고해 나만의 경조사비 기준도 수월하게 세워보세요.

## 주요기능
1. 보낸돈 / 받은돈 저장, 수정, 조회, 삭제 기능
   1. 종류별(결혼식, 생일, 돌잔치, 장례식 등) 카테고리 지정 기능
   2. 각 내역 별 메모 기능
2. 이름, 종류, 월별 검색 기능
3. 보낸돈 / 받은돈 전체 건수 및 합계금액 표시
4. 날짜순, 종류순, 금액순으로 내역 정렬
5. 전체 유저의 연령별, 종류별 통계 차트 표시

# 프로젝트 진행 시 고민한 사항들

## API 설계 방식에 대한 고민
1. URL Path 로 검색 조건을 분기하는 방식
   - ex. http://localhost:8080/records/event-types/{type}
   - 첫번째 방식은 상위 리소스와 하위 리소스의 계층적인 구조를 풀어서 작성했다기 보다 RecordBook 이 가지는 속성을 URL Path 에 적었다.
     REST API 에서는 URL 을 리소스를 계층적인 구조로 설계하는걸 더 지향하므로, 두번째 방식을 택했다.
2. Request Param(QueryString) 으로 검색 조건을 분기하는 방식
   - ex. http://localhost:8080/records?eventType={type}
   - 두번째 방식은 URL 을 계층적인 구조로 설계하고, 데이터 조회에 필요한 조건들은 Request Param(QueryString)으로 받아서 처리하도록 했다.
     이 경우 하나의 API 에서 Request Param 에 따라 여러 UseCase 가 존재할 수 있지만, Service 계층에서 잘 나누어 처리하는 방향으로 설계해본다.

## RecordBookService 와 GetRecordBookService 분리에 대한 고민
- 검색 조건이 다양해짐에 따라, RecordBookService 에 조회용 메서드가 많아지고 클래스 라인이 길어졌다. 
- 그래서 조회용 메서드들만 GetRecordBookService 클래스로 추출해 보았다.
