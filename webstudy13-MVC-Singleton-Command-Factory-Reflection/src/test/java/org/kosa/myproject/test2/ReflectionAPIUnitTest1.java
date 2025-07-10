package org.kosa.myproject.test2;

import java.lang.reflect.InvocationTargetException;

/**
  Reflection API : 런타임 시 동적으로 객체를 생성하고 제어
  
  
  일반적으로 필요 시 객체를 생성 : Person p = new Person(); 와 같이 new 로 생성
  Reflection API 는 런타임(프로그램 실행 중) 시에 동적 Dynamic으로 객체를 생성,
  제어하기 위한 기술
 */
public class ReflectionAPIUnitTest1 {
public static void main(String[] args) {
	Player player = null;
	String classInfo = "org.kosa.myproject.test2.Youtube";
	// Player 인터페이스 타입의 Youtube 객체를 new 없이 Reflection API 를 이용해
	// 동적으로 생성해본다.
	// 테스트 1에 기반하여 토론 -> refactoring : Youtube ==> command 연결
	// 						 해결해야 되는 지점은 패키지명 ==> 환경에 따라 수정될 수 있는 요소
	// 						 패키지 명을 문자열로 처리한다는 것(하드 코딩)은 이후 변경에 유연 x
	// 						 유지보수성이 낮다 ==> 패키지명을 동적으로 확보하는 방안으로 Test2를 구성
	// 										
	
	//클래스 로딩 -> 클래스의 메타 정보가 meta space에 저장됨 = class.forname()
	try {
		//구현체 정보가 없음 (new Youtube)
		player = (Player) Class.forName(classInfo).getDeclaredConstructor().newInstance();
		System.out.println(player+" 객체 확인");
		player.play();
	} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
			| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}
