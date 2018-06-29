package weskley;
import robocode.*;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * WeskleysRobot - a robot by Weskley
 */
public class WeskleysRobot extends AdvancedRobot
{
	/**
	 * run: WeskleysRobot's default behavior
	 */
	//Ao começar a partida, se direciona para as laterais do campo de batalha
	public void run() {
		setAhead(1000);
		setTurnRadarRight(360);
		
		while(true) {
			setTurnRadarRight(20);
			execute();
		}
	}
	
	//Ao localizar um robô inimigo, vira-se na direção dele e se estiver perto
	//o suficiente, atira com maior potência
	public void onScannedRobot(ScannedRobotEvent e) {
		setTurnGunRight(getHeading()-getGunHeading()+e.getBearing());
		if (e.getDistance() < 400)
		{
			fire(3);
		}
		else
		{
			fire(1);
		}
		scan();
	}

	//Ao ser atingido por uma bala, volta o canhão para a posição de onde
	//foi atingido e move-se para a frente
	public void onHitByBullet(HitByBulletEvent e) {
		setTurnGunRight(getHeading()-getGunHeading()+e.getBearing());
		setAhead(50);
	}
	
	//Ao se chocar contra a parede, vira 90 graus
	public void onHitWall(HitWallEvent e) {
		setTurnRight(90);
	}
	
	//Quando se chocar com outro robo, procura virar-se em direção do inimigo e fastar-se
	public void onHitRobot(HitRobotEvent e) {
		setTurnRight(e.getBearing());
		setBack(100);
		fire(3);
	}
}
