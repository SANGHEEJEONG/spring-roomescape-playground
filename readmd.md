## 예약
#### id :식별
#### name : 예약한 사람 이름
#### date : 예약한 날짜(방문하는 날짜)
+ 현재보다 이전이면 안 된다 <- 예약 생성 시에만 영향을 받는다
#### time : 방문하는 시각

## 외부 api 요청
#### Controller : 요청, 응답 DTO
#### Service
+ getReservations 예약 조회 메서드
+ createReservation 예약 추가 메서드
  + MissingParameterException
+ deleteReservation 예약 취소 메서드
  + NotFoundReservationException

