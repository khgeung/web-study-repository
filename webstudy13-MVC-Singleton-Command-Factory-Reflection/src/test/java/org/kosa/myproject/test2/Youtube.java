package org.kosa.myproject.test2;

public class Youtube implements Player {
	public Youtube() {
		System.out.println("Youtube 객체 생성");
	}
	@Override
	public void play() {
		System.out.println("유튜브 동영상을 재생하다");
	}

}
