package cn.crabime.redis.rwsep.controller;

import cn.crabime.redis.rwsep.beans.PGene;
import cn.crabime.redis.rwsep.service.PGeneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/mysql")
public class MySqlDbController {

    private final static Logger logger = LoggerFactory.getLogger(MySqlDbController.class);

    @Autowired
    private PGeneService pGeneService;

    @RequestMapping(value = "/fg", method = RequestMethod.GET)
    @ResponseBody
    public PGene findGeneBySpecies(HttpServletRequest request) throws InterruptedException {
        String geneId = request.getParameter("geneId");
        // 模拟RPC操作
        Thread.sleep(3000);
        return pGeneService.findGeneById(geneId);
    }

    @RequestMapping(value = "/insgene", consumes = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public PGene insertGene(@RequestBody PGene gene) {
        String geneId = gene.getGeneId();
        PGene pg = pGeneService.findGeneById(geneId);
        if (pg != null) {
            logger.info("数据已存在");
            return null;
        }
        pGeneService.insertGene(gene);
        return gene;
    }
}
