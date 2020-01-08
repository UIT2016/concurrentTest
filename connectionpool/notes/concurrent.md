## 并发

阻塞和非阻塞是说，进程是否由运行态变成阻塞态，这时，
操作系统将会将该进程挂在阻塞队列上，并选择其他就绪进程上CPU，所以，
阻塞对当前进程是种时间和效率上的浪费。阻塞是说，当前操作会引起进程进入阻塞态，
例如普通的打开文件操作，就会阻塞自己，直到内核返回。而非阻塞是否，当前操作不会引起进入阻塞态。

那么同步阻塞，同步非阻塞，异步非阻塞，异步阻塞是说什么呢？

其实，最后一个名词是个伪命题，异步不会引起进程进入阻塞态，例如上面的注册回调函数的操作。

同步阻塞就是我们普通的打开文件操作，同步非阻塞，是指，进程调用select等系统调用[3]，
而且把该系统调用的超时时间设置为0时，select函数会立即返回，不会阻塞当前进程，
不会引起进程进入阻塞态。而且，后面的指令也必须在select等执行完之后再执行，这二者一组合，
就变成了同步非阻塞。而异步非阻塞的意思就是异步操作了，上面已经提过了。

`//nio

         ServerSocketChannel channel = ServerSocketChannel.open();
       
 // 通过channel的配置可以配置为非阻塞
 
         channel.configureBlocking(false);
         channel.socket().bind(new InetSocketAddress(8080));
         Selector selector = Selector.open();
         channel.register(selector, SelectionKey.OP_ACCEPT);
         while (true) {
         
 // 虽然是非阻塞，也有阻塞的地方，在selector.select()的时候， 如果没有事件发生，会阻塞在这里。
 
             selector.select();
             Set<SelectionKey> keys = selector.selectedKeys();
             for (SelectionKey key : keys) {
                 if (key.isAcceptable()) {
                 
 // selector.select()已经阻塞了，这里客户端链接已经准备好了，所以会马上创建好tcp连接，这里是非阻塞的
 
                     SocketChannel client = channel.accept();
                     if (client == null) {
                         continue;
                     }
                     client.configureBlocking(false);
                     client.write(ByteBuffer.wrap("hello world".getBytes()));
                     
 // 给同一个selector注册客户端channel的read事件，因为一个selector能够注册多个channel，并且能够监听这些channel的accept，read，write等事件，所以叫做多路复用。  
 
                     client.register(selector, SelectionKey.OP_READ);
                 } else if (key.isReadable()) {
                     SocketChannel curClient = (SocketChannel) key.channel();
                     ByteBuffer bf = ByteBuffer.allocate(512);
                     
 // selector.select()的时候表明已经有数据过来了，所以这里是非阻塞
 
                     curClient.read(bf);
                     String ss = new String(bf.array(), "UTF-8");
                     System.out.println(ss);`
