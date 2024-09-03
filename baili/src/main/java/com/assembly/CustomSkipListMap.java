package com.assembly;

import com.baili.util.Utils;

public class CustomSkipListMap {

    //跳跃表头节点
    private CustomSkipListMapNode head;
    //跳跃表头结点的入口，即头结点最上层索引
    private CustomSkipListMapLevel headIndex;
    //跳跃表最高层数
    private int maxLevel;
    //跳跃表中节点数量
    private long length;

    class CustomSkipListMapNode {
        //节点成员对象，排序规则也是按照该值排序, 字符串的默认排序
        private String key;
        //节点对象值
        private int value;
        //是否被删除标记
        private boolean deleted;
        //后退指针
        private CustomSkipListMapNode backward;
        //前进指针
        private CustomSkipListMapNode forward;

        public CustomSkipListMapNode() {
        }

        public CustomSkipListMapNode(String key, int value, CustomSkipListMapNode backward, CustomSkipListMapNode forward) {
            this.key = key;
            this.value = value;
            this.backward = backward;
            this.forward = forward;
        }
    }

    class CustomSkipListMapLevel {
        //存放当前索引指向的最下层节点引用
        private CustomSkipListMapNode node;
        //同层的前方节点索引层
        private CustomSkipListMapLevel forward;
        //层之间移动 指向下层，最下层索引则为null，不指向数据节点
        private CustomSkipListMapLevel down;
        //该层的层数号，从下往上计数，headIndex只是一个入口引用，因此初始默认 = 0
        private int levelNum;

        public CustomSkipListMapLevel() {
        }

        public CustomSkipListMapLevel(CustomSkipListMapNode node, CustomSkipListMapLevel forward, CustomSkipListMapLevel down, int levelNum) {
            this.node = node;
            this.forward = forward;
            this.down = down;
            this.levelNum = levelNum;
        }
    }

    public CustomSkipListMap() {
        //初始化头结点和头结点入口
        this.head = new CustomSkipListMapNode();
        this.headIndex = new CustomSkipListMapLevel();
        this.headIndex.node = head;
        this.maxLevel = 0;
        this.length = 0;
    }

    /**
     * 向跳跃表中添加key，如果key存在则进行覆盖修改
     * @param key
     * @param value
     * @throws Exception
     */
    public void put(String key, int value) {
        //入参校验，日志输出
        Utils.isPresentOrElse(key).presentOrElseHandle((data) -> {
            System.out.println("start add key : " + key);
        }, () -> {throw new NullPointerException("key is null");});

        //声明需要插入的节点
        CustomSkipListMapNode insertNode;

        //寻找插入key的前置索引层下方节点
        //level1    ->     level1   该层为第一层索引层
        //  ↓                ↓
        //  b -> a ->  b  -> c
        CustomSkipListMapNode b = findPreviousNode(key);
        //从前置索引层节点开始找该区间内插入的key位置
        //即上图b -> a ->  d  -> c 中符合key的位置
        //这里使用 b -> n -> f 三个节点引用
        for (CustomSkipListMapNode n = b.forward;;){
            if (n != null) {
                CustomSkipListMapNode f = n.forward;
                //比较key和中间n节点的节点值大小
                int c = ((Comparable)key).compareTo(n.key);
                //如果key比n中的值大则三个引用右移
                if(c > 0){
                    b = n;
                    n = f;
                    continue;
                }
                //如果key和n中的值相同，说明是重复插入，这里先进行覆盖修改value
                // 后续可以考虑加入参数onlyIfAbsent表示是否只有未存在才进行插入
                if(c == 0){
                    n.value = value;
                    return; //修改完成后直接返回，无需后续的索引层指向变动
                }
            }

            // n==null，那说明插入的节点就是在该区间最后，
            // n !=null 而且没有从上面if中continue出去说明找到了插入位置
            //初始化一个新的节点，这里n其实就是插入节点的下一个指向节点,b则是前一个节点
            insertNode = new CustomSkipListMapNode(key, value, b, n);
            //修改前置节点的下一个节点指向
            b.forward = insertNode;
            //跳出循环
            break;
        }

        //到这里，成功找到了插入的位置，并且插入了节点，但是节点的索引层还未建立也还没有与其他节点索引层建立关联
        //随机插入节点的层数，如果层数超过当前跳跃表最大层数
        //则设置当前层数为最大层+1，这样做是为了跳跃表整体索引层有序，避免出现突然一个很高的无效索引层
        int level = 0;
        while ((int) (Math.random() * 100) <= 25){ //这里小于等于25是为了模拟 java中原来的 32位最高位和最低位都为0的情况，也是1/4的概率，  25/100
            if(++level > maxLevel){
                break;
            }
        }

        //根据索引层创建具体的层链接
        CustomSkipListMapLevel startLevel = null;
        for (int i = 1; i <= level; i++) {
            //这样创建是为了最后startLevel指向最上层的那个索引层，后续由上到下进行索引层关联
            startLevel = new CustomSkipListMapLevel(insertNode, null, startLevel, i);
        }

        //创建一个临时的入口索引层引用
        CustomSkipListMapLevel h = headIndex;

        //如果当前层数大于最大层，则头结点的索引层需要进行扩容，并且新扩出来的那一层直接指向插入节点最上层即可
        if(level > maxLevel){
            //因为headIndex的第0层是初始默认的，因此如果是初始状态，则直接将该层的层数变为1，前进指向更改即可
            if(headIndex.levelNum == 0){
                headIndex = new CustomSkipListMapLevel(head, startLevel, null, headIndex.levelNum + 1);
            }else {
                //非0层，则创建新的层数指向原下一层
                headIndex = new CustomSkipListMapLevel(head, startLevel, headIndex, headIndex.levelNum + 1);
            }
            //这种情况新插入节点最上层已经完成关联， 则向下移动一层完成下面层数的关联即可
            startLevel = startLevel.down;
            //需要初始的头结点索引位置也需要向下移动一层
            h = headIndex.down;
        }else if(level < maxLevel){
            //如果当前层数小于最大层,需要将开始的索引层引用调整到跟startLevel同一层
            for (int i = maxLevel - level; i > 0; i--){
                h = h.down;
            }
        }

        //最底层不需要关联索引层
        if(h != null) {
            //剩余索引层的关联
            //其实跟findPreviousNode方法中一样，
            //用q，r，d三个索引层引用来循环，q是前置索引层，r是后置索引层，d就是要处理的中间的索引层
            for (CustomSkipListMapLevel q = h, r = q.forward, d = startLevel;;){
                //到最底层索引时结束
                if (q == null || d == null) {
                    break;
                }

                if(r != null){
                    CustomSkipListMapNode n = r.node;
                    //比较key和r节点key大小，如果key大 则q和r往右移动，
                    //目的是像findPreviousNode一样找到最小的前置索引层
                    if (((Comparable)key).compareTo(n.key) > 0) {
                        q = r;
                        r = r.forward;
                        continue;
                    }
                }

                //将d指向r，q指向d，完成d的索引层插入
                if(r != null){
                    d.forward = r;
                }
                q.forward = d;

                //将d，q，r指向下一层
                d = d.down;
                q = q.down;
                if (q != null){
                    r = q.forward;
                }
            }
        }
        //跳跃表节点数量增加一
        length ++;
        //更新跳跃表最大层数
        maxLevel = Math.max(maxLevel, level);
        return;
    }

    /**
     * 移除跳跃表中指定key
     * 移除节点后，跳跃表节点数量减少，但是最大层数不变，即跳跃表整体层数只会扩容不会缩容
     * 移除节点后，该节点的索引层并未移除，只是打上了标记
     * 后续其他对跳跃表的操作中会由findPreviousNode方法删除关联，不影响使用
     * @param key
     */
    public void remove(String key) {
        //入参校验，日志输出
        Utils.isPresentOrElse(key).presentOrElseHandle((data) -> {
            System.out.println("start remove key : " + key);
        }, () -> {throw new NullPointerException("key is null");});
        
        //找到需要删除的节点前置索引层节点
        CustomSkipListMapNode b = findPreviousNode(key);

        //从前置索引层节点往后找到删除的节点
        for (CustomSkipListMapNode n = b.forward;;){
            //没有找到需要删除的节点，则直接返回
            if(n == null){
                break;
            }

            CustomSkipListMapNode f = n.forward;
            //比较key和中间n节点的节点值大小
            int c = ((Comparable)key).compareTo(n.key);
            //key比n小，说明key不在表中，没有找到删除的节点，直接返回
            if(c < 0){
                break;
            }

            //如果key比n中的值大则三个引用右移
            if(c > 0){
                b = n;
                n = f;
                continue;
            }

            //能走到这里说明c==0，则找到了删除的节点就是n,直接修改b的forward为f即可
            b.forward = f;
            f.backward = b;
            //把n节点删除标记设置为true，延迟删除该节点关联的索引层节点，由findPreviousNode方法进行处理
            n.deleted = true;
            //跳跃表节点数量修改
            length--;
            break;
        }
        return;
    }

    /**
     * 从跳跃表中查询指定key
     * @param key
     * @return
     */
    public Integer get(String key) {
        //入参校验，日志输出
        Utils.isPresentOrElse(key).presentOrElseHandle((data) -> {
            System.out.println("start get key : " + key);
        }, () -> {throw new NullPointerException("key is null");});

        //找到key节点前置索引层节点
        CustomSkipListMapNode b = findPreviousNode(key);

        //从前置索引层节点往后找到删除的节点
        for (CustomSkipListMapNode n = b.forward;;){
            //没有找到需要删除的节点，则直接返回
            if(n == null){
                break;
            }

            CustomSkipListMapNode f = n.forward;
            //比较key和中间n节点的节点值大小
            int c = ((Comparable)key).compareTo(n.key);
            //key比n小，说明key不在表中，没有找到删除的节点，直接返回
            if(c < 0){
                break;
            }

            //如果key比n中的值大则三个引用右移
            if(c > 0){
                b = n;
                n = f;
                continue;
            }

            //能走到这里说明c==0，则找到了节点key就是n
            return n.value;
        }
        return null;
    }

    /**
     * 使用q，r，d三个索引层的引用 进行循环 寻找插入key的前置节点
     * 初始q指向跳跃表入口层引用 headIndex,r指向q的前方（同层右侧）索引层
     * 比较key和r层下方节点中的值
     * 如果key大，则q指向r，r则指向r的右侧索引层，完成索引层间的右移操作
     * 如果key小，则需要进行索引层的下移，这时候d指向q的下层，q指向d，那么r指向q的右侧索引层
     * 循环即可找到合适的索引的区间
     * @param key
     * @return
     */
    private CustomSkipListMapNode findPreviousNode(String key) {
        for (CustomSkipListMapLevel q = headIndex, r = q.forward, d;;){
            if(r != null){
                CustomSkipListMapNode rNode = r.node;
                //说明该节点是删除的节点，修改q的forward指向r的后续一个节点
                if(rNode.deleted){
                    q.forward = r.forward;
                    //重新设置r的引用指向
                    r = q.forward;
                    continue;
                }

                //插入key分值大于右侧节点，则索引层右移
                if (((Comparable)key).compareTo(rNode.key) > 0) {
                    q = r;
                    r = r.forward;
                    continue;
                }
            }

            //如果r为null，则索引层向下移动，到最底层时（插入的key的分值大于所有现有节点值）
            // 说明q节点就是最后一个节点了
            if((d = q.down) == null){
                return q.node;
            }
            //如果还有下层则进行索引层下移
            q = d;
            r = q.forward;
        }
    }

    /**
     * 按照这样的形式输出跳跃表内容
     * level1    ->     level1
     *   ↓                ↓
     *   b ->  a ->  b -> c
     *
     */
    public void printSkipListLayout() {
        if(length == 0){
            System.out.println("跳跃表无元素");
        }

        //打印入口headIndex
        System.out.print("headIndex");

        //打印索引层
        CustomSkipListMapLevel lineLevel = this.headIndex;
        while (lineLevel != null && lineLevel.levelNum > 0 && !lineLevel.node.deleted){
            System.out.println(String.format("\n%-10s", "↓"));
            System.out.print(String.format("%-10s", "level-" + lineLevel.levelNum));

            CustomSkipListMapLevel colLevel = lineLevel.forward;
            while (colLevel != null && !colLevel.node.deleted){
                System.out.print(String.format("%-10s", "→"));
                System.out.print(String.format("%-10s", "level-" + colLevel.levelNum));
                colLevel = colLevel.forward;
            }
            lineLevel = lineLevel.down;
        }

        //打印节点数据
        CustomSkipListMapNode node = this.headIndex.node;
        System.out.println(String.format("\n%-10s", "↓"));
        System.out.print(String.format("%-10s", node.key));
        while (node.forward!= null){
            node = node.forward;
            System.out.print(String.format("%-10s", "→"));
            System.out.print(String.format("%-10s", node.key));
        }
        System.out.println();
    }

}
