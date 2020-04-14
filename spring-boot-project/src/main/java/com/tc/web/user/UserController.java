package com.tc.web.user;

import com.tc.common.Constant;
import com.tc.common.PageResult;
import com.tc.model.TUser;
import com.tc.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: tianchao
 * @Date: 2020/4/10 21:21
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping
    public ResponseEntity<Void> saveUser(TUser user){
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> editUser(TUser user){
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/page")
    public ResponseEntity<PageResult<TUser>> queryTUserPage(
            @RequestParam(value = "page",defaultValue = Constant.DEFAULT_PAGE) Integer page,
            @RequestParam(value = "rows",defaultValue = Constant.DEFAULT_ROWS) Integer rows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "desc",defaultValue = "false") Boolean desc,
            @RequestParam(value = "key",required = false) String key
    ){
        return ResponseEntity.ok(userService.queryTUserPage(page,rows,sortBy,desc,key));
    }
}
