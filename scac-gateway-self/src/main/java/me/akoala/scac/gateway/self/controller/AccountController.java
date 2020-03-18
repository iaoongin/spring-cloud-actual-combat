package me.akoala.scac.gateway.self.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.akoala.scac.gateway.self.entity.Account;
import me.akoala.scac.gateway.self.service.AccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Account)表控制层
 *
 * @author makejava
 * @since 2020-03-17 14:46:08
 */
@RestController
@RequestMapping("account")
public class AccountController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private AccountService accountService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param account 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Account> page, Account account) {
        return success(this.accountService.page(page, new QueryWrapper<>(account)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.accountService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param account 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Account account) {
        return success(this.accountService.save(account));
    }

    /**
     * 修改数据
     *
     * @param account 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Account account) {
        return success(this.accountService.updateById(account));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.accountService.removeByIds(idList));
    }
}