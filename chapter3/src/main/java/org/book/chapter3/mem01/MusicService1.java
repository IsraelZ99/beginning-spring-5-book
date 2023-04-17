package org.book.chapter3.mem01;

import org.book.chapter3.AbstractMusicService;
import org.springframework.stereotype.Component;

// An abstract class in Java can not be instantiated, so telling Spring that a abstract component is a component
// would be ... unfortunate
@Component
public class MusicService1 extends AbstractMusicService {
}
