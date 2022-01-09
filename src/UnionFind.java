public class UnionFind
{
    private int[] m_parent;  // parent[i] = parent of i
    private int[] m_size;    // size[i] = number of sites in tree rooted at i

    public UnionFind(int N)
    {
        m_parent = new int[N];
        m_size = new int[N];
        for (int i = 0; i < N; i++) {
            m_parent[i] = i;
            m_size[i] = 1;
        }
    }

    public int Find(int p)
    {
        int n = m_parent.length;
        if (p < 0 || p >= n)
            return -1;

        int root = p;
        while (root != m_parent[root])
            root = m_parent[root];

        while (p != root)
        {
            int new_p = m_parent[p];
            m_parent[p] = root;
            p = new_p;
        }
        return root;
    }

    public void Union(int p, int q)
    {
        int root_p = Find(p);
        int root_q = Find(q);
        if (root_p == root_q) return;

        // make smaller root point to larger one
        if (m_size[root_p] < m_size[root_q])
        {
            m_parent[root_p] = root_q;
            m_size[root_q] += m_size[root_p];
        }
        else
        {
            m_parent[root_q] = root_p;
            m_size[root_p] += m_size[root_q];
        }
    }
}
