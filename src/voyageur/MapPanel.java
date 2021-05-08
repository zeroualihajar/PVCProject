package voyageur;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import outils.Ville;

public class MapPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public List<Ville> villes = new ArrayList<Ville>();
	public List<Ville> chemins = new ArrayList<Ville>();//Route

	public Image imageVille;
	public int WIDTH_MAP = 500;
	public int HEIGHT_MAP = 300;
	public Image imageBackground;

	/**
	 * Create the panel.
	 */
	public MapPanel() {
		setBackground(new Color(245, 255, 250));
		this.setBounds(0, 0, WIDTH_MAP, HEIGHT_MAP);
		setLayout(null);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				String retour = JOptionPane.showInputDialog("Saisir le nom de la ville : ");
				final int x = e.getX();
				final int y = e.getY();

				try {
					// imageVille = new
					// ImageIcon(MapPanel.class.getResource("/images/city"+(villes.get(villes.size()-1).getIdVille()+1)+".png")).getImage();//ImageIO.read(new
					// File("/images/" + "corona.jpeg"));
					imageVille = new ImageIcon(MapPanel.class.getResource("/images/city1.png")).getImage();
					// System.out.println("/images/city"+(villes.get(villes.size()-1).getIdVille()+1)+".png");
//					this.VilleImageCenter = new Dimension(this.VilleImage.getWidth() / 2, this.VilleImage.getHeight() / 2);
				} catch (final Exception ex) {
					imageVille = null;
//					this.VilleImageCenter = new Dimension(0, 0);
				}

				villes.add(new Ville(villes.size() + 1, retour, x, y, false));

				System.out.println("Ville : " + retour + "\n(x,y) : (" + e.getX() + ", " + e.getY()
						+ "\nNbre de villes : " + villes.size() + "\n");
				repaint();
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g2d);

		for(Ville ville : villes) {
			paintVille(g2d, ville);
		}
	}

	/* ----- L'arri�re plan (la carte) du panel ----- */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);

		this.imageBackground = new ImageIcon(MapPanel.class.getResource("/images/map.png")).getImage();

//		int width = imageBackground.getWidth(this)/2;
//		int height = imageBackground.getHeight(this)/2;

		if (imageBackground != null) {
//			int x = this.getParent().getWidth() / 2 - width;
//			int y = this.getParent().getHeight() / 2 - height;
			g.drawImage(imageBackground, 0, 0, this);
		}
	}

	/* ----- Dessin des villes ----- */
	public void paintVille(Graphics2D g, Ville ville) {
		int x = ville.getPosX();
		int y = ville.getPosY();
//		int centerX = this.VilleImageCenter.width;
//		int centerY = this.VilleImageCenter.height;

		// Black background
		g.setColor(Color.RED);
		g.fillOval(x - 45, y - 25, 10 * 2, 10 * 2);

		// ville image
		g.drawImage(imageVille, x - 45, y - 25, null);

		// Number background
//		g.setColor(Color.WHITE);
//		g.fillOval(x + 3, y + 3, 14, 14);

		// Number
		g.setColor(Color.WHITE);

		g.drawString(ville.getNomVille(), x + 1, y + 17);
	}

	/* ----- Dessin d'une route entre deux villes ----- */
	public void paintChemin(Graphics2D g, Ville ville1, Ville ville2) {
		if (ville1 != null && ville2 != null) {
			int x1 = ville1.getPosX();
			int y1 = ville1.getPosY();
			int x2 = ville2.getPosX();
			int y2 = ville2.getPosY();

			g.setColor(Color.RED);
			g.setStroke(new BasicStroke(2));
			g.drawLine(x1, y1, x2, y2);
		}
	}
}
