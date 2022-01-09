import java.util.Arrays;

public class Grid
{
    private final int m_N;
    private boolean[] m_cells;
    private UnionFind m_uf;
    private int m_opened_count;

    public Grid(int N)
    {
        m_N = N;
        m_cells = new boolean[N * N];
        Arrays.fill(m_cells, false);
        m_opened_count = 0;
        m_uf = new UnionFind(N * N + 2);
    }

    public void Open (int i, int j)
    {
        int idx = i * m_N + j;
        if (m_cells[idx])
            return;

        m_cells[idx] = true;
        m_opened_count++;

        if (i == 0)
            m_uf.Union(idx, m_N * m_N);
        if (i == m_N - 1)
            m_uf.Union(idx, m_N * m_N + 1);

        if ((i > 0) && m_cells[(i - 1) * m_N + j])
            m_uf.Union(idx, (i - 1) * m_N + j);
        if ((i < m_N - 1) && m_cells[(i + 1) * m_N + j])
            m_uf.Union(idx, (i + 1) * m_N + j);
        if ((j > 0) && m_cells[i * m_N + j - 1])
            m_uf.Union(idx, i * m_N + j - 1);
        if ((j < m_N - 1) && m_cells[i * m_N + j + 1])
            m_uf.Union(idx, i * m_N + j + 1);
    }

    public boolean HasPercolation()
    {
        return m_uf.Find(m_N * m_N) == m_uf.Find(m_N * m_N + 1);
    }

    public int OpenedCount()
    {
        return m_opened_count;
    }
}