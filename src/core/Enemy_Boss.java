package core;

import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import globalData.Constant;
import main.BulletController;
import main.GameUI;
import main.Timer;

public class Enemy_Boss extends Enemy{
	
	private Random random;
	private int randomLeft;
	private int randomRight;
	private double speed_x;
	private double speed_y;
	private boolean flag;
	private Timer timer;

	public Enemy_Boss(GameUI gameUI, int x, int y, String type) throws IOException {
		super(gameUI, x, y, type);
		defaultSetting();
		getImage();
	}

	private void defaultSetting() {
		this.maxLife = 30; 
		this.HP = maxLife;
		this.speed   = 1;
		this.speed_x = speed;
		this.speed_y = speed;
		this.width   = (10*12);
		this.height  = (10*12);
		this.flag = true;
		this.bulletType = "BossBullet";
		this.bullet = new BulletController(gameUI,500);
		
		//position setting
		random = new Random();
		randomLeft = random.nextInt(Constant.screenWidth/2+1);
		randomRight = random.nextInt(Constant.screenWidth/2+1)+Constant.screenWidth/2;
	}
	
	private void getImage() throws IOException {
		bufferedImage = ImageIO.read(getClass().getResourceAsStream("/enemy/enemy01.png"));
	}
	
	private void changeImage() throws IOException {
		bufferedImage = ImageIO.read(getClass().getResourceAsStream("/enemy/enemyDie.png"));
	}

	
	@Override
	public void update() throws IOException {
		if(checkIfDie()<0){
		x += speed_x;
		y += speed_y;
		if(speed_x > 0) {  
			if((x+width) >= randomRight || (x+width) >= Constant.screenWidth) {
				speed_x *= -1;
				randomRight = random.nextInt(183)+182;
			}
		}else{  
			if(x <= randomLeft || x <= 0) {
				speed_x *= -1;
				randomLeft = random.nextInt(183);
			}
		}
		if(speed_y > 0) {
			if(y>=100) 
				speed_y *= -1;
		}
		else {
			if(y <= 10) 
				speed_y *= -1;
		}
		
		//bullet
		
			letBullet();
		}
		
		
		super.checkCollision();
		checkIfDie();
		changeToWinnerScreen();
	}
	
	public void letBullet() throws IOException {
		if(bullet.canFire())
			bullet.fireBullet(bulletType, x+(width/2), y+(height), this);
		
	}

	public boolean changeToWinnerScreen() {
		if (timer != null && timer.TimeToZero())
			return true;
		return false;
	}

	public int checkIfDie() throws IOException {
		if(HP <= 0 && flag) {
			timer = new Timer(1500);
			flag =  false;
			changeImage();
		}
		if(HP<=0)
			return 3343;
		return -1;
	}
	

}
